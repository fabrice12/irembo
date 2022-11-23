package io.rhenez.irembotest.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationResponse {
    private Long userId;
    private String response;
    private String comment;
}
