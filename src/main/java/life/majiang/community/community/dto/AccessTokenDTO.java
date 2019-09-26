package life.majiang.community.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-07-17 22:35
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
