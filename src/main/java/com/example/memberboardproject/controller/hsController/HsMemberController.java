package com.example.memberboardproject.controller.hsController;

import com.example.memberboardproject.dto.hsDto.HsMemberDTO;
import com.example.memberboardproject.service.hsService.HsMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hsMember")
public class HsMemberController {
    private final HsMemberService hsMemberService;

    @GetMapping("/save")
    public String hsMemberSave() {
        return "/HSPages/hsMemberPages/hsMemberSave";
    }

    @PostMapping("/save")
    public String saveHsMember(@ModelAttribute HsMemberDTO hsMemberDTO) throws IOException {
        hsMemberDTO.setMemberMobile(hsMemberDTO.getMemberNum1() + "-" + hsMemberDTO.getMemberNum2() + "-" + hsMemberDTO.getMemberNum3());
        hsMemberDTO.setMemberBirth(hsMemberDTO.getMemberYY() + "-" + hsMemberDTO.getMemberMM() + "-" + hsMemberDTO.getMemberDD());
        hsMemberService.save(hsMemberDTO);
        return "/HSPages/hsMemberPages/hsMemberLogin";
    }
    @PostMapping("/emailCheck")
    public ResponseEntity emailCheck (@RequestBody String memberEmail) {
        if (hsMemberService.findByMemberEmail(memberEmail)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
