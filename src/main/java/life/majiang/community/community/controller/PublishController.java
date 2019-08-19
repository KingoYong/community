package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-08-19 21:32
 **/
@Controller
public class PublishController {
    @GetMapping
    public String publish(){
        return "publish";
    }
}
