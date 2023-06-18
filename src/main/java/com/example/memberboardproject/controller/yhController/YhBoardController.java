package com.example.memberboardproject.controller.yhController;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhCommentDTO;
import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.service.yhService.YhBoardService;
import com.example.memberboardproject.service.yhService.YhCommentService;
import com.example.memberboardproject.service.yhService.YhMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String boardList(@PageableDefault(page = 1) Pageable pageable,
                            @RequestParam(value = "type", defaultValue = "", required = false) String type,
                            @RequestParam(value = "q", defaultValue = "", required = false) String q,
                            Model model) {
        System.out.println("컨트롤러에 있는 type = " + type + "q" + q);
        Page<YhBoardDTO> yhBoardDTOList = yhBoardService.findPage(pageable, type, q);
        int limitPage = 5;
        int startPage = ((int) Math.ceil(((double) pageable.getPageNumber() / limitPage)) - 1) * limitPage + 1;
        int endPages = yhBoardDTOList.getTotalPages() > (startPage + limitPage - 1) ? startPage + limitPage - 1 : yhBoardDTOList.getTotalPages();
        if (yhBoardDTOList == null) {
            model.addAttribute("boardList", null);
        } else {
            model.addAttribute("boardList", yhBoardDTOList);
        }
        System.out.println("yhBoardDTOList" + yhBoardDTOList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPages);
        model.addAttribute("type", type);
        model.addAttribute("q", q);
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
        yhBoardService.updateBoard(yhBoardDTO);
        return "redirect:list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        yhBoardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
