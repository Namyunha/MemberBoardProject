package com.example.memberboardproject.controller.yhController;

import com.example.memberboardproject.dto.yhdDto.YhCommentDTO;
import com.example.memberboardproject.service.yhService.YhCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/yhComment")
public class YhCommentController {
    private final YhCommentService yhCommentService;

    @PostMapping("/save")
    public ResponseEntity saveComment(@RequestBody YhCommentDTO yhCommentDTO, Model model) {
        System.out.println("post로 들어온 yhCommentDTO = " + yhCommentDTO);
        yhCommentService.save(yhCommentDTO);
        List<YhCommentDTO> commentDTOList = yhCommentService.findAll(yhCommentDTO.getBoardId());
        model.addAttribute("commentList", commentDTOList);
        System.out.println("컨트롤러에있는 commentDTOList = " + commentDTOList);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

}
