package io.rhenez.irembotest.services;

import io.rhenez.irembotest.models.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Async
    public void sendNewUserEmail(User user, String subject, String message,String password) {
        try{
            Context context = new Context();
            context.setVariable("user", user);
            context.setVariable("message", message);
            context.setVariable("password", password);

            String process = templateEngine.process("emails/new_user", context);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setSubject(subject);
            helper.setText(process, true);
            helper.setTo(user.getEmail());
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void sendSimpleEmail(User user, String subject, String message) {
        try{
            Context context = new Context();
            context.setVariable("user", user);
            context.setVariable("message", message);


            String process = templateEngine.process("emails/simple_email", context);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setSubject(subject);
            helper.setText(process, true);
            helper.setTo(user.getEmail());
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
