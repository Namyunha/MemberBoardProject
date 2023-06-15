package com.example.memberboardproject.controller.jyController;


import com.example.memberboardproject.dto.jyDto.JyCommentDTO;
import com.example.memberboardproject.service.jyService.JyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jy/comment")
public class JyCommentController {
    private final JyCommentService jyCommentService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody JyCommentDTO jyCommentDTO) {
        try {
            jyCommentService.save(jyCommentDTO);
            List<JyCommentDTO> jyCommentDTOList = jyCommentService.findAll(jyCommentDTO.getBoardId());
            return new ResponseEntity<>(jyCommentDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
