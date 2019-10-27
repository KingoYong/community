package life.majiang.community.community.controller;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.dto.ResultDTO;
import life.majiang.community.community.model.Comment;
import life.majiang.community.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-27 13:40
 **/
@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        /*User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.err(CustomizeErrCode.NO_LOGIN);
        }*/
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
//        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.ok();
    }
}
