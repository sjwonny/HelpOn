package com.example.helpon.mapper;

import com.example.helpon.dto.Desired_dayDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface Desired_dayMapper {
    public void insert(Desired_dayDto desired_dayDto);
}
