package com.example.helpon.mapper;

import com.example.helpon.dto.ClientDto;
import com.example.helpon.vo.ClientVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    public void insert(ClientDto clientDto);
    public List<ClientVo> selectList();
}
