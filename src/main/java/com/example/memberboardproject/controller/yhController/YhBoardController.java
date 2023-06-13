package com.example.memberboardproject.controller.yhController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@RequestMapping("/yhBoard")
public class YhBoardController {
    @GetMapping("/list")
    public String boardList(HttpSession session, Model model) {
        String loginDTO = (String)session.getAttribute("loginDTO");
        System.out.println("보드컨트롤러에있는 loginDTO = " + loginDTO);
        model.addAttribute("loginEmail", loginDTO);
        return "/YHPages/yhBoardPages/yhBoardList";
    }


}
