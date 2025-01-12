package com.example.helpon.mapper;

import com.example.helpon.dto.BoardDto;
import com.example.helpon.vo.BoardVo;
import com.example.helpon.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public void insert(BoardDto boardDto);
    public void delete(Long boardNumber);
    public void update(BoardDto boardDto);
    public BoardVo select(Long boardNumber);
    public List<BoardVo> selectAll(Criteria criteria);
    public int selectTotal();
}
