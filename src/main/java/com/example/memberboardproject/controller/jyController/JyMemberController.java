package com.example.memberboardproject.controller.jyController;

import com.example.memberboardproject.dto.jyDto.JyMemberDTO;
import com.example.memberboardproject.service.jyService.JyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/JYPages";
    }

    @GetMapping("/main")
    public String mainForm() { return "JYPages/jyMemberPages/jyMemberMain"; }

    @GetMapping("/mypage")
    public String findById(HttpSession session, Model model) {
        Long loginId = (Long) session.getAttribute("loginId");
        JyMemberDTO jyMemberDTO = jyMemberService.findById(loginId);
        model.addAttribute("member", jyMemberDTO);
        return "JYPages/jyMemberPages/jyMemberMyPage";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        JyMemberDTO jyMemberDTO = jyMemberService.findById(id);
        model.addAttribute("member", jyMemberDTO);
        return "JYPages/jyMemberPages/jyMemberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute JyMemberDTO jyMemberDTO) {
        jyMemberService.update(jyMemberDTO);
        return "redirect:/jy/member/mypage";
    }
}
