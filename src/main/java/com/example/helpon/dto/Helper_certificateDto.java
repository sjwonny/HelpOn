package com.example.helpon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Helper_certificateDto {
    private Long helperCertificateNumber;
    private Long helperNumber;
    private Long certificateNumber;

}
