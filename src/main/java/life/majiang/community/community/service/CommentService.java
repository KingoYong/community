package life.majiang.community.community.service;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.exception.CustomizeErrCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.CommentMapper;
import life.majiang.community.community.mapper.QuestionExtMapper;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-27 16:13
 **/
@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private QuestionExtMapper questionExtMapper;
    @Resource
    private UserMapper userMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrCode.TARGET_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrCode.QUESTION_ERR);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> listByQuestionId(Integer id) {
        //通过问题id找到对问题的评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        //将评论实体利用stream获得去重的评论者id的list
        List<Integer> userIds = comments.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());
        //利用之前id的list来获取评论者list
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        //将user的list变为map，方便后面直接将评论者信息放在评论实体中，传到页面
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        //转换comment为commentDTO，加入评论者的完整信息
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }
}
