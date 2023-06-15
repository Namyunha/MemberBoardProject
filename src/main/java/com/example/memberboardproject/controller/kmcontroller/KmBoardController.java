package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.service.kmService.KmBoardService;
import com.example.memberboardproject.service.kmService.KmMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kmBoard")
public class
KmBoardController {
    private final KmBoardService kmBoardService;
    private final KmMemberService kmMemberService;

    @GetMapping("/save")
    public String boardSaveForm() {
        return "KMPages/kmBoardPages/kmBoardSave";

    }

    @PostMapping("/save")
    public String boardSave(@ModelAttribute KmBoardDTO kmBoardDTO, HttpSession session,
                            Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        System.out.println("저장 kmBoardDTO = " + kmBoardDTO);
        Long saveId = kmBoardService.save(kmBoardDTO, loginEmail);
        model.addAttribute("saveResult", saveId);
        return "KMPages/kmBoardPages/kmBoardList";

    }

    @GetMapping("/boardList")
    public String findAll(Model model) {
        List<KmBoardDTO> kmBoardDTOList = kmBoardService.findAll();
        System.out.println("가져온글목록kmBoardDTOList = " + kmBoardDTOList);
        model.addAttribute("boardList", kmBoardDTOList);
        return "KMPages/kmBoardPages/kmBoardList";

    }

}
