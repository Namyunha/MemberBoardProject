package com.example.memberboardproject.controller.yhController;


import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.service.yhService.YhMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/yhMember")
@RequiredArgsConstructor
public class YhMemberController {
    private final YhMemberService yhMemberService;

    @GetMapping("/save")
    public String saveForm() {
        return "/YHPages/yhMemberSave";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute YhMemberDTO yhMemberDTO) {
        System.out.println("컨트롤러에있는 yhMemberDTO = " + yhMemberDTO);
        yhMemberService.save(yhMemberDTO);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/YHPages/yhMemberLogin";
    }


}
