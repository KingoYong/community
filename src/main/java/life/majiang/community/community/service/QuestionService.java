package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.exception.CustomizeErrCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.QuestionExtMapper;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.QuestionExample;
import life.majiang.community.community.model.User;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-09-27 21:26
 **/
@EqualsAndHashCode
@Service
@Component
public class QuestionService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO getList(Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(totalCount, pageNum, pageSize);
        //当页码小于1时，设置为1；大于总页数时，设置为总页数
        pageNum = pageNum > paginationDTO.getTotalPage() ? paginationDTO.getTotalPage() : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        //计算sql中查询的偏移量
        Integer offset = pageSize * (pageNum - 1);
        List<Question> questionList = questionExtMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, pageSize));
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将一个对象的属性直接copy到另一个对象的属性中，这里将question的属性直接放到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public PaginationDTO getListByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(example);
        paginationDTO.setPagination(totalCount, pageNum, pageSize);
        //当页码小于1时，设置为1；大于总页数时，设置为总页数
        pageNum = pageNum > paginationDTO.getTotalPage() ? paginationDTO.getTotalPage() : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        //计算sql中查询的偏移量
        Integer offset = pageSize * (pageNum - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionExtMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, pageSize));
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将一个对象的属性直接copy到另一个对象的属性中，这里将question的属性直接放到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){//新建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        } else {//更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            //更新要区分：这里使用updateByExampleSelective而不是updateByPrimaryKey
            //updateByExampleSelective:是将不为null的参数更新，为null不更新
            //updateByPrimaryKey；是将传送过去的对象所以参数都更新（包括为null的参数）
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incViewCount(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incViewCount(question);
    }
}
