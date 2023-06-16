package com.example.memberboardproject.controller.yhController;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhCommentDTO;
import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.service.yhService.YhBoardService;
import com.example.memberboardproject.service.yhService.YhCommentService;
import com.example.memberboardproject.service.yhService.YhMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/yhBoard")
public class YhBoardController {
    private final YhBoardService yhBoardService;
    private final YhMemberService yhMemberService;
    private final YhCommentService yhCommentService;
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
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        YhBoardDTO yhBoardDTO = yhBoardService.findById(id);
        System.out.println("컨트롤러에 있는 yhBoardDTO = " + yhBoardDTO);
        List<YhCommentDTO> commentDTOList = yhCommentService.findAll(id);
        String loginEmail = (String) session.getAttribute("loginDTO");
        YhMemberDTO writerDTO = yhMemberService.findByBoardId(id);
        System.out.println("컨트롤러에 있는 writerDTO = " + writerDTO);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("writerDTO", writerDTO);
        model.addAttribute("yhBoard", yhBoardDTO);
        return "/YHPages/yhBoardPages/yhDetail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute YhBoardDTO yhBoardDTO) throws IOException {
        System.out.println("컨트롤러에 있는 update : yhBoardDTO = " + yhBoardDTO);
        yhBoardService.deleteFile(yhBoardDTO.getId());
//        yhBoardService.updateBoard(yhBoardDTO);
        return "redirect:list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        yhBoardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
