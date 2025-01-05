package com.example.helpon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CertificateDto {
    private Long certificateNumber;
    private String certificate;
}
