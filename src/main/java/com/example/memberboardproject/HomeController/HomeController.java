package com.example.memberboardproject.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/HSPages")
    public String HSPages() {
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
