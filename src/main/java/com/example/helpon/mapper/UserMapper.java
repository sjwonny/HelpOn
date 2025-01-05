package com.example.helpon.mapper;

import com.example.helpon.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public void insert(UserDto userDto);
}
