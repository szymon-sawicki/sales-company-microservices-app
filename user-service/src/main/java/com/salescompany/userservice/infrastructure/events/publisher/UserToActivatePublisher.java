package com.salescompany.userservice.infrastructure.events.publisher;

import com.salescompany.userservice.application.service.EventPublisher;
import com.salescompany.userservice.application.service.dto.UserToActivateDto;
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
