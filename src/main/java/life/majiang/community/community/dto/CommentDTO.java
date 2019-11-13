package life.majiang.community.community.dto;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-11-12 08:47
 **/
@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
