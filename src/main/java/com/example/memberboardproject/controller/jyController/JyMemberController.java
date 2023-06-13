package com.example.memberboardproject.controller.jyController;

import com.example.memberboardproject.dto.jyDto.JyMemberDTO;
import com.example.memberboardproject.service.jyService.JyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jy/member")
public class JyMemberController {
    private final JyMemberService jyMemberService;

    @GetMapping("/save")
    public String saveForm() { return "JYPages/jyMemberPages/jyMemberSave"; }

    @PostMapping("/email-check")
    public ResponseEntity emailCheck(@RequestBody JyMemberDTO jyMemberDTO) {
        boolean result = jyMemberService.emailCheck(jyMemberDTO.getMemberEmail());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute JyMemberDTO jyMemberDTO) throws IOException {
        jyMemberService.save(jyMemberDTO);
        return "JYPages/jyMemberPages/jyMemberLogin";
    }

    @GetMapping("/login")
    public String loginForm() { return "JYPages/jyMemberPages/jyMemberLogin"; }

    @PostMapping("/login")
    public String login(@RequestParam String memberEmail,
                        @RequestParam String memberPassword,
                        HttpSession session) {
        JyMemberDTO jyMemberDTO = jyMemberService.login(memberEmail, memberPassword);
        if (jyMemberDTO == null) {
            return "JYPages/jyMemberPages/jyMemberLogin";
        } else {
            session.setAttribute("loginEmail", memberEmail);
            session.setAttribute("loginId", jyMemberDTO.getId());
            return "JYPages/jyMemberPages/jyMemberMain";
        }
    }
}
