package io.rhenez.irembotest.services;

import io.rhenez.irembotest.Dto.SmsRequest;
import io.rhenez.irembotest.feignClients.OrionSMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    //token from properties
    @Value("${sms.token}")
    private  String SMSToken;
    @Value("${sms.sendername}")
    private String senderName;

    @Autowired
    private  OrionSMSClient orionSMSClient;




    public void sendSMS(String phoneNumber, String message) {
        SmsRequest request= new SmsRequest(SMSToken, phoneNumber, message, senderName);
        orionSMSClient.sendSMS(request);
    }
}
