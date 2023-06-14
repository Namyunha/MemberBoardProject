package com.example.memberboardproject.controller.jyController;

import com.example.memberboardproject.dto.jyDto.JyBoardDTO;
import com.example.memberboardproject.service.jyService.JyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jy/board")
public class JyBoardController {
    private final JyBoardService jyBoardService;

    @GetMapping("/save")
    public String saveForm() { return "JYPages/jyBoardPages/jyBoardSave"; }

    @PostMapping("/save")
    public String save(@ModelAttribute JyBoardDTO jyBoardDTO) throws IOException {
        System.out.println("jyBoardDTO = " + jyBoardDTO);
        jyBoardService.save(jyBoardDTO);
        return "redirect:/jy/board/save";
    }
}
