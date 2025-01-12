package com.example.helpon.service;


import com.example.helpon.dto.BoardDto;
import com.example.helpon.mapper.BoardMapper;
import com.example.helpon.vo.BoardVo;
import com.example.helpon.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    public void register(BoardDto boardDto){
        if(boardDto == null){
            throw new IllegalArgumentException("게시물 정보가 없습니다.");
        }
        boardMapper.insert(boardDto);
    }

    public void remove(Long boardNumber){
        if(boardNumber == null){throw new IllegalArgumentException("존재하지 않는 게시물");}
        boardMapper.delete(boardNumber);
        fileService.remove(boardNumber);
    }

    public void modify(BoardDto boardDto){
        if(boardDto == null){
            throw new IllegalArgumentException("게시물 수정 정보가 없습니다.");
        }
        boardMapper.update(boardDto);
    }

    public void modify(BoardDto boardDto, List<MultipartFile> files) throws IOException{
        if(boardDto == null || files == null){
            throw new IllegalArgumentException("게시물 수정 정보가 없습니다.");
        }
        fileService.remove(boardDto.getBoardNumber());
        fileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
        boardMapper.update(boardDto);
    }

    /**
     * @Param boardNumber
     * @return
     * @throws IllegalArgumentException 게시물 정보가 존재하지 않으면 발생한다.
     */
    @Transactional(readOnly = true)
    public BoardVo findBoard(Long boardNumber){
        if(boardNumber == null){throw new IllegalArgumentException("존재하지 않는 게시물");}
        return Optional.ofNullable(boardMapper.select(boardNumber))
                .orElseThrow(()-> {throw new IllegalArgumentException("존재하지 않는 게시물 번호");});
    }

    //전체조회
    @Transactional(readOnly = true)
    public List<BoardVo> findAll(Criteria criteria){
        return boardMapper.selectAll(criteria);
    }

    //전체 게시물 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return boardMapper.selectTotal();
    }
}
