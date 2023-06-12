package com.example.memberboardproject.controller.yhController;


import com.example.memberboardproject.dto.NaemMemberDTO;
import com.example.memberboardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class YhMemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute NaemMemberDTO memberDTO) {
        System.out.println("컨트롤러에 있는 memberDTO = " + memberDTO);

        return "redirect:login";
    }

}
