package com.example.memberboardproject.controller.SwController;

import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.service.swService.SwMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        System.out.println("DTO = " + DTO);
        if(DTO!=null) {
            session.setAttribute("loginEmail",DTO.getMemberEmail());
            System.out.println("1");
            return new ResponseEntity<>(DTO,HttpStatus.OK);
        }else {
            System.out.println("2");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/member/logout")
    public String memberLogout(HttpSession session) {
        session.invalidate();
        return "/SWPages";
    }
}
