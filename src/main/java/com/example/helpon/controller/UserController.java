package com.example.helpon.controller;

import com.example.helpon.dto.*;
import com.example.helpon.service.FileService;
import com.example.helpon.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("/index")
    public void index(){}

    @GetMapping("/login")
    public void login(){}

    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req){
        try {
            Long userNumber = userService.findUserNumber(userId, userPassword);
            req.getSession().setAttribute("userNumber", userNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("/user/login");
        }
        return new RedirectView("/user/login");
    }


    @GetMapping("/client/join")
    public String clientJoin(Model model){
        List<DayDto> dayList =  userService.getDay();
        List<TimeDto> timeList =  userService.getTime();
        model.addAttribute("dayList", dayList);
        model.addAttribute("timeList", timeList);
        return "user/clientJoin";
    }

    @PostMapping("/client/join")
    public RedirectView clientJoin(UserDto userDto, ClientDto clientDto, @RequestParam List<Long> dayNumbers
            , @RequestParam("profileFile") MultipartFile file ){
        userService.register(userDto, clientDto, dayNumbers);

        if(!file.isEmpty()){
            try {
                fileService.registerAndSaveFile(file, userDto.getUserNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/user/login");
    }

    @GetMapping("/helper/join")
    public String helperJoin(Model model){
        List<CertificateDto> certificateList =  userService.getCertificate();
        model.addAttribute("cList", certificateList);
        return "user/HelperJoin";
    }

    @PostMapping("/helper/join")
    public RedirectView helperJoin(UserDto userDto, HelperDto helperDto, @RequestParam List<Long> certificateNumbers
            , @RequestParam("profileFile") MultipartFile file ){
        userService.helperRegister(userDto, helperDto, certificateNumbers);
        if(!file.isEmpty()){
            try {
                fileService.registerAndSaveFile(file, userDto.getUserNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new RedirectView("/user/login");
    }
}
