package com.example.helpon.mapper;

import com.example.helpon.dto.ClientDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper {
    public void insert(ClientDto clientDto);
}
