package com.example.memberboardproject.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/HSPages")
    public String HSPages(HttpServletRequest request, Model model) {
        String address = request.getRequestURI();
        boolean showBtn = address.equals("/HSPages");
        model.addAttribute("showBtn", showBtn);
        return "/HSPages/index";
    }
    @GetMapping("/SWPages")
    public String SWPages() {
        return "/SWPages/index";
    }
    @GetMapping("/JYPages")
    public String JYPages() {
        return "/JYPages/index";
    }
    @GetMapping("/KMPages")
    public String KMPages() {
        return "/KMPages/index";
    }
    @GetMapping("/YHPages")
    public String YHPages() {
        return "/YHPages/index";
    }
}
