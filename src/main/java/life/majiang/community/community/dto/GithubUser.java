package life.majiang.community.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-07-20 10:31
 **/
@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;
}
