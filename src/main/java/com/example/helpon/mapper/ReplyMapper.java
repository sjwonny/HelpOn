package com.example.helpon.mapper;

import com.example.helpon.dto.ReplyDto;
import com.example.helpon.vo.Criteria;
import com.example.helpon.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    public void insert(ReplyDto replyDto);
    public void delete(Long replyNumber);
    public void update(ReplyDto replyDto);
    public ReplyVo select(Long replyNumber);
    public List<ReplyVo> selectList(Long boardNumber);
    public List<ReplyVo> selectListPage(@Param("criteria") Criteria criteria, @Param("boardNumber")Long boardNumber);
    public int selectTotal(Long boardNumber);
}
