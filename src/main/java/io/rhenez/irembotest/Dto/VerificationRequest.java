package io.rhenez.irembotest.Dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private  String identityType;
    private String identityNumber;
    private String identityAttachment;
    private LocalDateTime createdAt;


}
