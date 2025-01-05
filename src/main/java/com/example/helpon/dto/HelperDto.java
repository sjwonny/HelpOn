package com.example.helpon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class HelperDto {
    private Long helperNumber;
    private String helperBirth;
    private String helperGender;
    private String isCall;
    private String introduction;
}
