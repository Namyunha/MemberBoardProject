package com.example.memberboardproject.controller.kmcontroller;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.service.kmService.KmBoardService;
import com.example.memberboardproject.service.kmService.KmMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kmBoard")
public class KmBoardController {
    private final KmBoardService kmBoardService;
    private final KmMemberService kmMemberService;

    @GetMapping("/save")
    public String boardSaveForm() {
        return "KMPages/kmBoardPages/kmBoardSave";

    }

    @PostMapping("/save")
    public String boardSave(@ModelAttribute KmBoardDTO kmBoardDTO, HttpSession session,
                            Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        System.out.println("저장 kmBoardDTO = " + kmBoardDTO);
        Long saveId = kmBoardService.save(kmBoardDTO, loginEmail);
        model.addAttribute("saveResult", saveId);
        return "redirect:/kmBoard/boardList";
    }

    @GetMapping("/boardList")
    public String findAll(Model model) {
        List<KmBoardDTO> kmBoardDTOList = kmBoardService.findAll();
        System.out.println("가져온글목록kmBoardDTOList = " + kmBoardDTOList);
        model.addAttribute("boardList", kmBoardDTOList);
        return "KMPages/kmBoardPages/kmBoardList";
    }

    @GetMapping("/boardPaging")
    public String paging(@PageableDefault(page = 1) Pageable pageable,
                         @RequestParam(value = "type", required = false, defaultValue = "") String type,
                         @RequestParam(value = "q", required = false, defaultValue = "") String q,
                         Model model) {
        System.out.println("page = " + pageable.getPageNumber());
        System.out.println("pageable = " + pageable);
        System.out.println("type" + type);
        System.out.println("q" + q);

        Page<KmBoardDTO> kmBoardDTOS = kmBoardService.paging(pageable, type, q);
        System.out.println("페이징을 위해 가져온kmBoardDTOS = " + kmBoardDTOS);
        model.addAttribute("boardList", kmBoardDTOS);
        // 시작페이지(startPage), 마직막 페이지(endPage) 값 계산
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < kmBoardDTOS.getTotalPages()) ? startPage + blockLimit - 1 : kmBoardDTOS.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q", q);
        model.addAttribute("type", type);
        return "KMPages/kmBoardPages/kmBoardPaging";
    }
    @GetMapping("/boardDetail/{id}")
    public String findById(@PathVariable Long id,
                           @RequestParam("page") int page,
                           @RequestParam("type") String type,
                           @RequestParam("q") String q,
                           Model model) {
        System.out.println("id = " + id + ", page = " + page +
                ", type = " + type + ", q = " + q + ", model = " + model);
        kmBoardService.boardHits(id);
        model.addAttribute("type", type);
        model.addAttribute("page", page);
        model.addAttribute("q", q);
        KmBoardDTO boardDTO = kmBoardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "KMPages/kmBoardPages/kmBoardDetail";
    }
}
