package com.example.helpon.controller;

import com.example.helpon.dto.ReplyDto;
import com.example.helpon.service.ReplyService;
import com.example.helpon.vo.Criteria;
import com.example.helpon.vo.PageVo;
import com.example.helpon.vo.ReplyVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;

    //  REST에서 post는 create를 처리한다.
    @PostMapping("/reply")
    public String replyRegister(@RequestBody ReplyDto replyDto, HttpServletRequest req) {
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        replyDto.setUserNumber(userNumber);

        replyService.register(replyDto);
        return "작성 성공!";
    }

    //    get은 조회를 담당한다.
    @GetMapping("/list/{boardNumber}")
    public List<ReplyVo> getReplyList(@PathVariable("boardNumber")Long boardNumber){
        return replyService.findList(boardNumber);
    }

    //    수정을 위한 method는 Patch와 Put이 있다.
//    Patch : 일부 수정
//    Put : 전체 수정
//    위와 같이 나누어 사용하지만 크게 구분하지 않는 경우가 많다.
    @PatchMapping("/{replyNumber}")
    public void replyModify(@PathVariable("replyNumber") Long replyNumber,
                            @RequestBody ReplyDto replyDto){
        replyDto.setReplyNumber(replyNumber);
        replyService.modify(replyDto);
    }

    //    PathVariable을 2개 이상 설정할 수도 있다.
//    @PatchMapping(value = {"/{replyNumber}", "/{replyNumber}/{replyContent}"})
//    public void replyModify(@PathVariable("replyNumber") Long replyNumber,
//                            @PathVariable(value = "replyContent", required = false) String replyContent,
//                            @RequestBody ReplyDto replyDto){
//        replyDto.setReplyNumber(replyNumber);
//        replyDto.setReplyContent(replyContent);
//        replyService.modify(replyDto);
//    }

    //    url : /리플번호
//    해당 번호 리플을 삭제한다.
    @DeleteMapping("/{replyNumber}")
    public void replyRemove(@PathVariable("replyNumber") Long replyNumber){
        replyService.remove(replyNumber);
    }

    //    url : /리플번호
//    댓글 1개 조회하기
    @GetMapping("/{replyNumber}")
    public ReplyVo replySearch(@PathVariable("replyNumber") Long replyNumber){
        return replyService.findReply(replyNumber);
    }

    @GetMapping("/list/{boardNumber}/{page}")
    public Map<String, Object> replyListPage(@PathVariable("boardNumber") Long boardNumber,
                                             @PathVariable("page") int page){
        Criteria criteria = new Criteria(page, 10);
        PageVo pageVo = new PageVo(criteria, replyService.findTotal(boardNumber));
        List<ReplyVo> replyList = replyService.findListPage(criteria, boardNumber);
        Map<String, Object> replyMap = new HashMap<>();
        replyMap.put("pageVo", pageVo);
        replyMap.put("replyList", replyList);

        return replyMap;
    }
}
