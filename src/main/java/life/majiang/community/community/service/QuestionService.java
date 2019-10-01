package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-09-27 21:26
 **/
@Service
@Component
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO getList(Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        Integer totalCount = questionMapper.getCount();
        paginationDTO.setPagination(totalCount, pageNum, pageSize);
        //当页码小于1时，设置为1；大于总页数时，设置为总页数
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageNum = pageNum > paginationDTO.getTotalPage() ? paginationDTO.getTotalPage() : pageNum;
        //计算sql中查询的偏移量
        Integer offset = pageSize * (pageNum - 1);
        List<Question> questionList = questionMapper.getList(offset, pageSize);
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将一个对象的属性直接copy到另一个对象的属性中，这里将question的属性直接放到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }
}
