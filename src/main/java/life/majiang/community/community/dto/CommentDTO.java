package life.majiang.community.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-27 13:42
 **/
@Data
public class CommentDTO {
    private int parentId;
    private int type;
    private String content;
}
