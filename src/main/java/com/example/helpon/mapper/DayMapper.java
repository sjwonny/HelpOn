package com.example.helpon.mapper;

import com.example.helpon.dto.DayDto;
import com.example.helpon.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//이 인터페이스를 빈으로 등록하고 매퍼를 사용하기 위한 인터페이스인 것을
//알려주기 위해 @Mapper 어노테이션을 사용
@Mapper
public interface DayMapper {

    public List<DayDto> getDay();
}
