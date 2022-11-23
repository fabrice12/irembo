package io.rhenez.irembotest.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequest {
    private String token;
    private String phone;
    private String message;
    @JsonProperty(value = "sender_name")
    private String senderName;
}
