package com.example.memberboardproject.controller.SwController;

import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.service.swService.SwMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sw")
public class SwMemberController {
    private final SwMemberService swMemberService;

    @GetMapping("/member/save")
    public String memberSaveForm() {
        return "/SWPages/memberPages/memberSave";
    }

    @PostMapping("/member/save")
    public String memberSave(@ModelAttribute SwMemberDTO swMemberDTO) throws IOException {
        swMemberService.save(swMemberDTO);
        return "/SWPages/index";
    }
}
