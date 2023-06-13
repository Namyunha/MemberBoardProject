package com.example.memberboardproject.controller.hsController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hsMember")
public class HsMemberController {
    @GetMapping("/save")
    public String hsMemberSave(HttpServletRequest request , Model model){
        String address = request.getRequestURI();
        boolean showBtn = address.equals("/hsMember/save");
        System.out.println("showBtn = " + showBtn);
        model.addAttribute("showBtn", showBtn);
        return "/HSPages/hsMemberPages/hsMemberSave";
    }
}
