package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.service.kmService.KmMemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/kmMember")
@RequiredArgsConstructor
public class KmMemberController {
    private final KmMemberService kmMemberService;

    @GetMapping("/save")
    public String saveForm() {
        return "KMPages/kmMemberPages/kmMemberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute KmMemberDTO kmMemberDTO, Model model) throws IOException {
        System.out.println("컨트롤러kmMemberDTO = " + kmMemberDTO);

        kmMemberService.save(kmMemberDTO);

        return "redirect:/kmMember/list";
    }

    @GetMapping("/list")
    public String findMemberAll(Model model) {
        List<KmMemberDTO> kmMemberDTOList = kmMemberService.findAll();
//        System.out.println("kmMemberDTOList = " + kmMemberDTOList);
        model.addAttribute("memberList", kmMemberDTOList);
        return "KMPages/kmMemberPages/kmMemberList";
    }

    @GetMapping("/login")
    public String LoginForm() {

        return "KMPages/kmMemberPages/kmMemberLoginForm";
    }

    @PostMapping("/login")
    public String memberLogin(@ModelAttribute KmMemberDTO kmMemberDTO,
                              HttpSession session) {
        System.out.println("로그인kmMemberDTO = " + kmMemberDTO);
        int loginResult = kmMemberService.loginChk(kmMemberDTO);
        if (loginResult == 1) {
            System.out.println("로그인 성공");
            session.setAttribute("loginEmail", kmMemberDTO.getMemberEmail());
            System.out.println("세션값=" + session.getAttribute("loginEmail"));
            return "redirect:/kmMember/list";
        } else {
            System.out.println("로그인 실패");
            return "redirect:/kmMember/login";
        }
    }

    @PostMapping("/emailDupleChk")
    public ResponseEntity memberDupleChk(@RequestBody KmMemberDTO kmMemberDTO) {
        String email =kmMemberDTO.getMemberEmail();
        boolean dupleResult = kmMemberService.findByEmail(email);
//        System.out.println("dupleResult = " + dupleResult);
        if (dupleResult) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

