package life.majiang.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-07-17 22:31
 **/
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        //这里利用JSON.toJSONString()方法将对象转换为json字符串
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                //这里的url是github的AccessToken接口，通过此接口从github获得AccessToken字符串
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //这里利用字符串的split("分隔符")方法将返回的结果分隔，取到所需的AccessToken字符串
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //这里的url是github的user接口，通过此接口携带AccessToken从github获得user的相应信息
                // 因为是get方法所以将参数放在url的后面
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //把Sting的json对象自动转化为java的类对象(本例中是转化为GithubUser对象)
            return JSON.parseObject(string, GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
