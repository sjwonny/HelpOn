package com.example.helpon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Desired_dayDto {
    private Long desiredDayNumber;
    private Long clientNumber;
    private Long dayNumber;

}
