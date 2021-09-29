package com.salescompany.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class UsersServiceApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

 /*   @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setPort(1111);
        Properties props = new Properties();
        props.put("mail.smtps.auth", "true");
        sender.setJavaMailProperties(props);

        return sender;
    }*/

    public static void main(String[] args) {

        SpringApplication.run(UsersServiceApplication.class, args);


        /*
        TODO - w którym serwisie ogarniamy statystyki zakupów klientów ?
         */

    }


}
