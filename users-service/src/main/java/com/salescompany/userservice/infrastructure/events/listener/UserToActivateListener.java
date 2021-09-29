package com.salescompany.userservice.infrastructure.events.listener;

import com.salescompany.userservice.application.service.dto.UserToActivateDto;
import com.salescompany.userservice.infrastructure.events.exception.EventsException;
import com.salescompany.userservice.infrastructure.persistence.dao.UserEntityDao;
import com.salescompany.userservice.infrastructure.persistence.dao.VerificationTokenEntityDao;
import com.salescompany.userservice.infrastructure.persistence.entity.VerificationTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserToActivateListener {
    private final JavaMailSender mailSender;
    private final UserEntityDao userEntityDao;
    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Async
    @EventListener
    @Transactional
    public void sendActivationEmail(UserToActivateDto userToActivateDto) {
        var userId = userToActivateDto.getId();

        var userToActivate = userEntityDao
                .findById(userId)
                .orElseThrow(() -> new EventsException("Cannot find user to activate"));

        var token = UUID.randomUUID().toString().replaceAll("\\W", "");

        var verificationToken = VerificationTokenEntity
                .builder()
                .token(token)
                .user(userToActivate)
                .dateTime(LocalDateTime.now().plusHours(12))
                .build();

        var insertedVerificationToken = verificationTokenEntityDao.save(verificationToken);

        String mail = userToActivate.getMail();
        String subject = "Registration activation";
        String url = "http://localhost:8080/users/activation?token=" + token;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(url);

        mailSender.send(simpleMailMessage);

    }
}
