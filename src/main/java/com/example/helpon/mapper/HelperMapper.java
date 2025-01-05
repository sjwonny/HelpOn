package com.example.helpon.mapper;

import com.example.helpon.dto.HelperDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelperMapper {
    public void insert(HelperDto helperDto);
}
