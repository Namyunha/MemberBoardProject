package com.example.memberboardproject.controller.SwController;

import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.service.swService.SwMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/member/login")
    public String memberLoginForm() {
        return "/SWPages/memberPages/memberLogin";
    }

    @PostMapping("/email_check")
    public ResponseEntity email_check(@RequestBody SwMemberDTO swMemberDTO) {
        SwMemberDTO DTO = swMemberService.findByEmail(swMemberDTO.getMemberEmail());
        if(DTO == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/member/login")
    public ResponseEntity memberLogin(@RequestBody SwMemberDTO swMemberDTO, HttpSession session) throws Exception {
        SwMemberDTO DTO = swMemberService.findByEmailAndMemberPassword(swMemberDTO.getMemberEmail(),swMemberDTO.getMemberPassword());
        if(DTO!=null) {
            session.setAttribute("loginEmail",DTO.getMemberEmail());

            return new ResponseEntity<>(DTO,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/member/logout")
    public String memberLogout(HttpSession session) {
        session.invalidate();
        System.out.println(session);
        return "redirect:/SWPages";
    }
    
    @GetMapping("/member/mypage")
    @Transactional
    public String memberMyPage(HttpSession session,Model model) {
        SwMemberDTO swMemberDTO = swMemberService.findByEmail((String) session.getAttribute("loginEmail"));
        model.addAttribute("memberDTO",swMemberDTO);
        return "/SWPages/memberPages/memberMyPage";
    }

    @Transactional
    @GetMapping("/member/updateForm/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        SwMemberDTO swMemberDTO = swMemberService.findById(id);
        model.addAttribute("memberDTO",swMemberDTO);
        return "/SWPages/memberPages/memberUpdate";
    }

    @PostMapping("/member/update")
    public String memberUpdate(@ModelAttribute SwMemberDTO swMemberDTO) throws IOException {
        swMemberService.memberUpdate(swMemberDTO);
        return "redirect:/SWPages";
    }
    @GetMapping("/member/memberDelete/{id}")
    public String memberDelete(@PathVariable Long id) {
        swMemberService.memberDelete(id);
        return "redirect:/sw/member/logout";
    }


}
