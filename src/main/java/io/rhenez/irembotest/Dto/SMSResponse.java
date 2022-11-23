package io.rhenez.irembotest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSResponse {
    private String response;
    private int statusCode;
}
