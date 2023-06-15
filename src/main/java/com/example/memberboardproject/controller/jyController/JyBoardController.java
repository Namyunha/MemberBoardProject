package com.example.memberboardproject.controller.jyController;

import com.example.memberboardproject.dto.jyDto.JyBoardDTO;
import com.example.memberboardproject.service.jyService.JyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jy/board")
public class JyBoardController {
    private final JyBoardService jyBoardService;

    @GetMapping("/save")
    public String saveForm() { return "JYPages/jyBoardPages/jyBoardSave"; }

    @PostMapping("/save")
    public String save(@ModelAttribute JyBoardDTO jyBoardDTO) throws IOException {
        System.out.println("jyBoardDTO = " + jyBoardDTO);
        jyBoardService.save(jyBoardDTO);
        return "redirect:/jy/board/save";
    }

    @GetMapping
    public String paging(@PageableDefault(page = 1)Pageable pageable,
                         @RequestParam(value = "type", required = false, defaultValue = "") String type,
                         @RequestParam(value = "q", required = false, defaultValue = "") String q,
                         Model model) {
        Page<JyBoardDTO> jyBoardDTOS = jyBoardService.paging(pageable, type, q);
        if (jyBoardDTOS.getTotalElements() == 0) {
            model.addAttribute("boardList", null);
        } else {
            model.addAttribute("boardList", jyBoardDTOS);
        }
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < jyBoardDTOS.getTotalPages()) ? startPage + blockLimit - 1 : jyBoardDTOS.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("type", type);
        model.addAttribute("q", q);
        return "JYPages/jyBoardPages/jyBoardPaging";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,
                           @RequestParam("page") int page,
                           @RequestParam("type") String type,
                           @RequestParam("q") String q,
                           Model model) {
        jyBoardService.updateHits(id);
        model.addAttribute("page", page);
        model.addAttribute("type", type);
        model.addAttribute("q", q);
        try {
            JyBoardDTO jyBoardDTO = jyBoardService.findById(id);
            model.addAttribute("board", jyBoardDTO);
            return "JYPages/jyBoardPages/jyBoardDetail";
        } catch (NoSuchElementException e) {
            return "JYPages/jyBoardPages/jyBoardNotFound";
        }
    }
}
