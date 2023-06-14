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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sw")
public class SwBoardController {
    private final SwBoardService swBoardService;
    private final SwMemberService swMemberService;

    @GetMapping("/board/boardSave")
    @Transactional
    public String boardSaveForm(HttpSession session, Model model) {
        SwMemberDTO swMemberDTO = swMemberService.findByEmail((String) session.getAttribute("loginEmail"));
        String memberName = swMemberDTO.getMemberName();
        Long member_id = swMemberDTO.getId();
        model.addAttribute("memberName",memberName);
        return "/SWPages/boardPages/boardSave";
    }

    @PostMapping("/board/boardSave")
    @Transactional
    public String boardSave(@ModelAttribute SwBoardDTO swBoardDTO) throws IOException {
        swBoardService.save(swBoardDTO);
        return "redirect:/SWPages";
    }

    @GetMapping("/board/boardList")
    @Transactional
    public String boardList(Model model) {
        List<SwBoardDTO> swBoardDTOList = swBoardService.findAll();
        for(int i=0; i<swBoardDTOList.size(); i++) {
            System.out.println(swBoardDTOList.get(i));
        }
        model.addAttribute("boardDTOList",swBoardDTOList);
        return "/SWPages/boardPages/boardList";

    }



}
