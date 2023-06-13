package com.example.memberboardproject.controller.yhController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/yhBoard")
public class YhBoardController {
    @GetMapping("/list")
    public String boardList() {
        return "/YHPages/yhBoardPages/yhBoardList";
    }


}
