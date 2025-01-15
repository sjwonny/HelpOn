package com.example.helpon.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
//@RequiredArgsConstructor는 final 또는 NonNull이 붙은 필드만 초기화하는 생성자를 만든다.
public class ReplyVo {
    private Long replyNumber;
    @NonNull
    private String replyContent;
    @NonNull
    private String replyRegisterDate;
    @NonNull
    private String replyUpdateDate;
    @NonNull
    private Long boardNumber;
    @NonNull
    private Long UserNumber;
    private String userId;

}
