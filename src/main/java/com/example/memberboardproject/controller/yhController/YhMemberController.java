package com.example.memberboardproject.controller.yhController;


import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.service.yhService.YhMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/yhMember")
@RequiredArgsConstructor
public class YhMemberController {
    private final YhMemberService yhMemberService;

    @GetMapping("/save")
    public String saveForm() {
        return "YHPages/yhMemberPages/yhMemberSave";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute YhMemberDTO yhMemberDTO) throws IOException {
        System.out.println("컨트롤러에있는 yhMemberDTO = " + yhMemberDTO);
        yhMemberService.save(yhMemberDTO);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "YHPages/YhMemberPages/yhMemberLogin";
    }

    @PostMapping("/login")
    public ResponseEntity loginMember(@RequestBody YhMemberDTO yhMemberDTO, HttpSession session) {
        YhMemberDTO loginDTO = yhMemberService.login(yhMemberDTO);
        session.setAttribute("loginDTO", yhMemberDTO.getMemberEmail());
        if (loginDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        String loginDTO = (String) session.getAttribute("loginDTO");
        YhMemberDTO yhMemberDTO = yhMemberService.findByEmail(loginDTO);
        model.addAttribute("memberDTO", yhMemberDTO);
        return "/YHPages/yhMemberPages/yhMyPage";
    }

    @PostMapping("/updateForm")
    public ResponseEntity updateForm(HttpSession session, Model model) {
        String loginDTO = (String) session.getAttribute("loginDTO");
        YhMemberDTO yhMemberDTO = yhMemberService.findByEmail(loginDTO);
        return new ResponseEntity<>(yhMemberDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody YhMemberDTO yhMemberDTO) {
        System.out.println("컨트롤러에 있는 yhMemberDTO = " + yhMemberDTO);
        yhMemberService.updateUser(yhMemberDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        System.out.println("id = " + id);
        yhMemberService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
