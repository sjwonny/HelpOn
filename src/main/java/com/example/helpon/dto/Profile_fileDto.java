package com.example.helpon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Profile_fileDto {
    private Long profileFileNumber;
    private String profileFileName;
    private String profileFileUploadPath;
    private String profileFileUuid;
    private Long userNumber;
}
