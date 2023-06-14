package com.example.memberboardproject.controller.jyController;

import com.example.memberboardproject.service.jyService.JyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jy/board")
public class JyBoardController {
    private final JyBoardService jyBoardService;

    @GetMapping("/save")
    public String saveForm() { return "JYPages/jyBoardPages/jyBoardSave"; }
}
