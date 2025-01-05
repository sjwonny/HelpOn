package com.example.helpon.controller;

import com.example.helpon.dto.ClientDto;
import com.example.helpon.dto.DayDto;
import com.example.helpon.dto.TimeDto;
import com.example.helpon.dto.UserDto;
import com.example.helpon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;

    @GetMapping("/index")
    public void index(){}

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/client/join")
    public String clientJoin(Model model){
       List<DayDto> dayList =  userService.getDay();
       List<TimeDto> timeList =  userService.getTime();
        model.addAttribute("dayList", dayList);
        model.addAttribute("timeList", timeList);
        return "user/clientJoin";
    }

    @PostMapping("/client/join")
    public RedirectView clientJoin(UserDto userDto, ClientDto clientDto, @RequestParam List<Long> dayNumbers){
        userService.register(userDto, clientDto, dayNumbers);
        System.out.println("userName 값: " + userDto.getUserName());
        System.out.println("Field1 값: " + userDto.getField1());
        return new RedirectView("/user/login");
    }
}
