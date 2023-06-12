package com.example.memberboardproject.controller.yhController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/yh")
@Controller
public class YhHomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
