package com.salescompany.userservice.application.service;

import com.salescompany.userservice.application.service.dto.UserToActivateDto;
import com.salescompany.userservice.application.service.exception.UsersServiceException;
import com.salescompany.userservice.domain.configs.validator.Validator;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.User;
import com.salescompany.userservice.domain.user.UserUtils;
import com.salescompany.userservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.userservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.domain.user.dto.validator.CreateUpdateUserDtoValidator;
import com.salescompany.userservice.domain.user.repository.UserRepository;
import com.salescompany.userservice.domain.verification_token.VerificationToken;
import com.salescompany.userservice.domain.verification_token.repository.VerificationTokenRepository;
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

      // TODO fix email activation  publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

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

    public GetUserDto findByMail(String mail) {
        if (mail == null) {
            throw new UsersServiceException("mail is null");
        }
        if (!mail.matches("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new UsersServiceException("mail have wrong format");
        }

        return userRepository.findByMail(mail)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UsersServiceException("cannot find user with given mail"));
    }

    public GetUserDto findByUsername(String username) {
        if (username == null) {
            throw new UsersServiceException("username is null");
        }
        if (!username.matches("[\\w\\s\\-'.]{5,20}+")) {
            throw new UsersServiceException("username have wrong format");
        }

        return userRepository.findByUsername(username)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UsersServiceException("cannot find user with given username"));
    }

    public GetUserDto findById(Long id) {
        if (id == null) {
            throw new UsersServiceException("id is null");
        }
        if (id <= 0) {
            throw new UsersServiceException("id is 0 or negative");
        }

        return userRepository.findById(id)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UsersServiceException("cannot find user with given id"));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetUserDto update(Long id, CreateUpdateUserDto createUpdateUserDto) {
        if (id == null) {
            throw new UsersServiceException("id is null");
        }
        if (id <= 0) {
            throw new UsersServiceException("id is 0 or negative");
        }
        Validator.validate(new CreateUpdateUserDtoValidator(),createUpdateUserDto);

        var userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new UsersServiceException("user with given id doesn't exist"));

        var userToUpdate = createUpdateUserDto.toUser()
                .withId(UserUtils.toId.apply(userFromDb))
                .withRole(UserUtils.toRole.apply(userFromDb))
                .withCreationDateTime(UserUtils.toCreationDateTime.apply(userFromDb));

        return userRepository.add(userToUpdate)
                .orElseThrow(() -> new UsersServiceException("cannot update user"))
                .toGetUserDto();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetUserDto delete(Long id) {
        if (id == null) {
            throw new UsersServiceException("id is null");
        }
        if (id <= 0) {
            throw new UsersServiceException("id is 0 or negative");
        }

        return userRepository.delete(id)
                .orElseThrow(() -> new UsersServiceException("cannot delete user"))
                .toGetUserDto();
    }


}
