package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.model.User;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-14 23:06
 **/
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          Model model,
                          @PathVariable("action") String action,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PaginationDTO paginationDTO = questionService.getListByUserId(user.getId(), pageNum, pageSize);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
