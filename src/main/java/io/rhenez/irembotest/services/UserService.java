package io.rhenez.irembotest.services;

import io.rhenez.irembotest.Dto.VerificationRequest;
import io.rhenez.irembotest.Dto.VerificationResponse;
import io.rhenez.irembotest.models.User;
import io.rhenez.irembotest.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final MailService mailService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
//    public User sendUserVerification(Long userId){
//        User user=userRepository.findById(userId).get();
//        user
//        kafkaTemplate.send("user-verification",user);
//        return user;
//    }
    public void sendUserVerification(VerificationRequest verificationRequest){
        kafkaTemplate.send("verification-request", verificationRequest);
    }
    public void simulateUserVerificationResponse(VerificationResponse verificationResponse){
        kafkaTemplate.send("verification-response", verificationResponse);
    }
    @KafkaListener(id = "Irembo",topics = "verification-response",groupId = "irembo")
    public void consumeVerificationResponse(VerificationResponse verificationResponse){
        User user=userRepository.findById(verificationResponse.getUserId()).get();
        if (verificationResponse.getResponse().equals("APPROVED")) {
            user.setStatus("VERIFIED");
        }else{
            user.setStatus("UNVERIFIED");

        }


        User userSaved=userRepository.save(user);
        if (userSaved.getStatus().equals("VERIFIED")) {
            mailService.sendSimpleEmail(user, "Account Verification", "Your account has been verified successfully");

        }else{
            mailService.sendSimpleEmail(user, "Account Verification", "Your account verification failed");

        }
    }
}
