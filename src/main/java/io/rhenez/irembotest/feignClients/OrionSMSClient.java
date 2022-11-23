package io.rhenez.irembotest.feignClients;

import io.rhenez.irembotest.Dto.SMSResponse;
import io.rhenez.irembotest.Dto.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OrionSMS", url = "https://sms.besoft.rw/api/v1")
public interface OrionSMSClient {
    @RequestMapping(method = RequestMethod.POST,value = "/client/bulksms")
    SMSResponse sendSMS(SmsRequest smsRequest);
}
