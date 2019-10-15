package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author create by King
 * @date 2019/6/26
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PaginationDTO pagination = questionService.getList(pageNum, pageSize);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
