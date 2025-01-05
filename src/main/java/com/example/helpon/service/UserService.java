package com.example.helpon.service;

import com.example.helpon.dto.*;
import com.example.helpon.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final DayMapper dayMapper;
    private final TimeMapper timeMapper;
    private final Desired_dayMapper desired_dayMapper;


    //클라이언트 회원 등록
    public void register(UserDto userDto, ClientDto clientDto, List<Long> dayNumbers){
        if(userDto == null){ throw new IllegalArgumentException("회원 정보 누락");}
        if(clientDto == null){ throw new IllegalArgumentException("의뢰인 정보 누락");}
        userMapper.insert(userDto);
        clientMapper.insert(clientDto);
        for (Long dayNumber : dayNumbers) {
            Desired_dayDto desiredDayDto = new Desired_dayDto();
            desiredDayDto.setClientNumber(clientDto.getClientNumber());
            desiredDayDto.setDayNumber(dayNumber);
            desired_dayMapper.insert(desiredDayDto);
        }
    }

    //요일 리스트 가져오기
    @Transactional(readOnly = true)
    public List<DayDto> getDay(){
       return dayMapper.getDay();
    }

    //시간 리스트 가져오기
    @Transactional(readOnly = true)
    public List<TimeDto> getTime(){
        return timeMapper.getTime();
    }

}
