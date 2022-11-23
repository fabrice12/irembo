package io.rhenez.irembotest;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IremboTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IremboTestApplication.class, args);
    }
    @Bean
    public NewTopic verificationRequest() {
        return TopicBuilder.name("verification-request")
                .build();
    }
    @Bean
    public NewTopic verificationResponse() {
        return TopicBuilder.name("verification-response")
                .build();
    }



}
