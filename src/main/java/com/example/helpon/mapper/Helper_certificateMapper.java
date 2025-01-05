package com.example.helpon.mapper;

import com.example.helpon.dto.Helper_certificateDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface Helper_certificateMapper {
    public void insert(Helper_certificateDto helper_certificateDto);
}
