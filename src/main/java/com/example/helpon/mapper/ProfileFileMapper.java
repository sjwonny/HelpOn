package com.example.helpon.mapper;

import com.example.helpon.dto.Profile_fileDto;
import com.example.helpon.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileFileMapper {
    public void insert(Profile_fileDto profile_fileDto);

    public void delete(UserDto userDto);

    public Profile_fileDto select(Long userNumber);
}
