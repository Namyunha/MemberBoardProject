package com.example.memberboardproject.controller.yhController;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.service.yhService.YhBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/yhBoard")
public class YhBoardController {
    private final YhBoardService yhBoardService;
    @GetMapping
    public String boardIndex() {
        return "/YHPages/yhBoardPages/index";
    }

    @GetMapping("/list")
    public String boardList(HttpSession session, Model model) {
        return "/YHPages/yhBoardPages/yhBoardList";
    }

    @GetMapping("/save")
    public String boardSaveForm() {
        return "/YHPages/yhBoardPages/yhBoardSave";
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute YhBoardDTO yhBoardDTO) throws IOException {
        System.out.println("컨트롤러로들어온 yhBoardDTO = " + yhBoardDTO);
        yhBoardService.boardSave(yhBoardDTO);
        return "redirect:list";
    }

}
