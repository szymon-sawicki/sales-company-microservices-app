package com.salescompany.usersservice.infrastructure.events.publisher;

import com.salescompany.usersservice.application.service.EventPublisher;
import com.salescompany.usersservice.application.service.dto.UserToActivateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToActivatePublisher implements EventPublisher<UserToActivateDto> {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publishEvent(UserToActivateDto userToActivateDto) {
        publisher.publishEvent(userToActivateDto);
    }
}
