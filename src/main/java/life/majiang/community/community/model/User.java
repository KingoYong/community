package life.majiang.community.community.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-07-21 15:13
 **/
@Data
@Component
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;
}
