package com.example.memberboardproject.controller.yhController;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.service.yhService.YhBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/yhBoard")
public class YhBoardController {
    private final YhBoardService yhBoardService;

    @GetMapping
    public String boardIndex() {
        return "/YHPages/yhBoardPages/index";
    }

    @Transactional
    @GetMapping("/list")
    public String boardList(Model model) {
        List<YhBoardDTO> yhBoardDTOList = yhBoardService.findAll();
        model.addAttribute("boardList", yhBoardDTOList);
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



    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        YhBoardDTO yhBoardDTO = yhBoardService.findById(id);
        System.out.println("컨트롤러에 있는 yhMemberDTO = " + yhBoardDTO);
        model.addAttribute("yhBoard", yhBoardDTO);
        return "/YHPages/yhBoardPages/yhDetail";
    }

}
