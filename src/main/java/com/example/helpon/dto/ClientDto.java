package com.example.helpon.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ClientDto {
    private Long clientNumber;
    private Long startTime;
    private Long endTime;
    private Long pay;
    private String require;
}
