package com.salescompany.usersservice.application.service;

import com.salescompany.usersservice.application.service.dto.UserToActivateDto;
import com.salescompany.usersservice.application.service.exception.UsersServiceException;
import com.salescompany.usersservice.domain.configs.validator.Validator;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.User;
import com.salescompany.usersservice.domain.user.UserUtils;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.usersservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.usersservice.domain.user.dto.validator.CreateUpdateUserDtoValidator;
import com.salescompany.usersservice.domain.user.repository.UserRepository;
import com.salescompany.usersservice.domain.verification_token.VerificationToken;
import com.salescompany.usersservice.domain.verification_token.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher<UserToActivateDto> publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createUser(CreateUpdateUserDto createUpdateUserDto, Role role) {
        Validator.validate(new CreateUpdateUserDtoValidator(), createUpdateUserDto);

        if (role == null) {
            throw new UsersServiceException("role is null");
        }

        if (userRepository.findByUsername(createUpdateUserDto.getUsername()).isPresent()) {
            throw new UsersServiceException("user with given username is already present in database");
        }
        if (userRepository.findByMail(createUpdateUserDto.getMail()).isPresent()) {
            throw new UsersServiceException("user with given e-mail is already present in database");
        }

        var userToInsert = createUpdateUserDto.toUser()
                .withPassword(passwordEncoder.encode(createUpdateUserDto.getPassword()))
                .withCreationDateTime(LocalDateTime.now())
                .withRole(role);

        var insertedUser = userRepository.add(userToInsert)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UsersServiceException("cannot insert user"));

        publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

        return insertedUser;

    }

    @Transactional
    public CreateUserResponseDto activateUser(String token) {
        if (token == null) {
            throw new UsersServiceException("Activation token is null");
        }

        return verificationTokenRepository
                .findByToken(token)
                .filter(VerificationToken::isValid)
                .flatMap(verificationToken -> userRepository
                        .findById(verificationToken.getUserId())
                        .map(user -> {
                            user.activate();
                            return userRepository.add(user).map(User::toCreateUserResponseDto)
                                    .orElseThrow(() -> new UsersServiceException("cannot activate user"));
                        }))
                .orElseThrow(() -> new UsersServiceException("User activation failed"));
    }


}
