package com.example.helpon.controller;


import com.example.helpon.dto.BoardDto;
import com.example.helpon.service.BoardService;
import com.example.helpon.service.FileService;
import com.example.helpon.vo.BoardVo;
import com.example.helpon.vo.Criteria;
import com.example.helpon.vo.PageVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String showBoardList(Criteria criteria, Model model){
        List<BoardVo> boardList = boardService.findAll(criteria);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageInfo", new PageVo(criteria, boardService.getTotal()));

        return "board/list";
    }

    @GetMapping("/write")
    public String showBoardForm(){return "board/boardWrite";}

    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto, HttpServletRequest req, RedirectAttributes redirectAttributes
            ,@RequestParam("boardFile") List<MultipartFile> files){
//  RedirectAttributes 는 리다이렉트 전용 Model객체라고 생각하면 된다.
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        boardDto.setUserNumber(userNumber);
        boardService.register(boardDto);
//        리다이렉트를 사용할 때 데이터를 전송하는 2가지 방법

//        쿼리스트링으로 데이터를 전송한다.(url에 쿼리스트링이 생긴다.)
//        권장하는 사용 방법 : 컨트롤러에서 데이터를 사용하는 경우
//        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());

//        플래쉬를 사용하여 데이터를 전송한다.
//        권장하는 사용 방법 : 화면에서 데이터를 사용하는 경우
        redirectAttributes.addFlashAttribute("boardNumber",boardDto.getBoardNumber());

        if(files != null){
            try {
                fileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/board/list");
    }
}
