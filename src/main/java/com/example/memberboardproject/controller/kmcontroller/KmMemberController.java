package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.service.kmService.KmMemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String findMemberAll(Model model){
        List<KmMemberDTO> kmMemberDTOList= kmMemberService.findAll();
        System.out.println("kmMemberDTOList = " + kmMemberDTOList);
        model.addAttribute("memberList",kmMemberDTOList);
        return "KMPages/kmMemberPages/kmMemberList";

    }

//    @PostMapping("/save")
//    public String save(@ModelAttribute KmMemberDTO kmMemberDTO) throws IOException {
//        kmMemberService.save(kmMemberDTO);
//        return "KMPages/kmMemberPages/kmMemberMain";
//    }

}

