package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kmMember")
public class KmMemberController {
    @GetMapping("/save")
    public String saveForm() {
        return "KMPages/kmMemberPages/kmMemberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute KmMemberDTO kmMemberDTO) {
        return "KMPages/kmMemberPages/kmMemberMain";
    }
}

