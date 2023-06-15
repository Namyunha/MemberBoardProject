package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.service.kmService.KmBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kmBoard")
public class
KmBoardController {
    private final KmBoardService kmBoardService;

    @GetMapping("/save")
    public String boardSaveForm() {

        return "KMPages/kmBoardPages/kmBoardSave";

    }
    @PostMapping("/save")
    public String boardSave(@ModelAttribute KmBoardDTO kmBoardDTO, Model model) throws IOException {
        System.out.println("저장 kmBoardDTO = " + kmBoardDTO);
        Long saveId = kmBoardService.save(kmBoardDTO);

        return "KMPages/kmBoardPages/kmBoardList";

    }

}