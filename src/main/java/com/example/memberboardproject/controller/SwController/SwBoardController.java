package com.example.memberboardproject.controller.SwController;

import com.example.memberboardproject.dto.SwDTO.SwBoardDTO;
import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.service.swService.SwBoardService;
import com.example.memberboardproject.service.swService.SwMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sw")
public class SwBoardController {
    private final SwBoardService swBoardService;
    private final SwMemberService swMemberService;

    @GetMapping("/board/save")
    @Transactional
    public String boardSaveForm(HttpSession session, Model model) {
        SwMemberDTO swMemberDTO = swMemberService.findByEmail((String) session.getAttribute("loginEmail"));
        System.out.println("swMemberDTO = " + swMemberDTO);
        model.addAttribute("memberDTO",swMemberDTO);
        return "/SWPages/boardPages/boardSave";
    }

}
