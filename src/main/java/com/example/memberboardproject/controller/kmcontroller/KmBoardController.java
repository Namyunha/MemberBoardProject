package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.service.kmService.KmBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kmBoard")
public class KmBoardController {
    private final KmBoardService kmBoardService;
    @GetMapping("/save")
    public String boardSave(@ModelAttribute KmBoardDTO kmBoardDTO){
        kmBoardService.save(kmBoardDTO);


        return "KMPages/kmBoardPages/kmBoardSave";

    }

}
