package com.example.helpon.service;

import com.example.helpon.dto.*;
import com.example.helpon.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final DayMapper dayMapper;
    private final TimeMapper timeMapper;
    private final Desired_dayMapper desired_dayMapper;
    private final CertificateMapper certificateMapper;
    private final HelperMapper helperMapper;
    private final Helper_certificateMapper helper_certificateMapper;

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

    //헬퍼 회원 등록
    public void helperRegister(UserDto userDto, HelperDto helperDto, List<Long> certificateNumbers){
        if(userDto == null || helperDto == null){throw new IllegalArgumentException("헬퍼 회원 정보 누락");}
        if(certificateNumbers == null){ throw new IllegalArgumentException("자격증이 선택되지 않았습니다");}
        userMapper.insert(userDto);
        helperMapper.insert(helperDto);
        for (Long certificateNumber : certificateNumbers) {
            Helper_certificateDto helperCertificateDto = new Helper_certificateDto();
            helperCertificateDto.setHelperNumber(helperDto.getHelperNumber());
            helperCertificateDto.setCertificateNumber(certificateNumber);
            helper_certificateMapper.insert(helperCertificateDto);
        }


    }

    //요일 리스트 가져오기
    @Transactional(readOnly = true)
    public List<DayDto> getDay(){
       return Optional.ofNullable(dayMapper.getDay())
               .orElseThrow(()-> {throw new IllegalArgumentException("요일이 존재하지 않습니다.");});
    }

    //시간 리스트 가져오기
    @Transactional(readOnly = true)
    public List<TimeDto> getTime(){
        return Optional.ofNullable(timeMapper.getTime())
                .orElseThrow(()-> {throw new IllegalArgumentException("시간이 존재하지 않습니다");});
    }

    // 자격증 리스트 가져오기
    @Transactional(readOnly = true)
    public List<CertificateDto> getCertificate(){
        return Optional.ofNullable(certificateMapper.getCertificate())
                .orElseThrow(()-> {throw new IllegalArgumentException("자격증이 존재하지 않습니다.");});
    }

    //유저 넘버 가져오기
    @Transactional(readOnly = true)
    public Long findUserNumber(String userId, String userPassword, Long userType){
        if(userId == null || userPassword == null) {throw new IllegalArgumentException("아이디, 패스워드 누락");};
        return Optional.ofNullable(userMapper.selectUserNumber(userId, userPassword, userType))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }

}
