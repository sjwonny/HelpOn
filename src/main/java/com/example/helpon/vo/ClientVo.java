package com.example.helpon.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
public class ClientVo {
    private Long clientNumber;
    private Long startTime;
    private Long endTime;
    private Long pay;
    private String require;
    private String userId;
    private String userName;
    private String userPhone;
    private String userAddress;
    private String field1;
    private String field2;
    private String choice;
}
