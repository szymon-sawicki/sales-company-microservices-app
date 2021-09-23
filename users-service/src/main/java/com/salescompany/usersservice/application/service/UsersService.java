package com.salescompany.usersservice.application.service;

import com.salescompany.usersservice.application.service.dto.UserToActivateDto;
import com.salescompany.usersservice.application.service.exception.UsersServiceException;
import com.salescompany.usersservice.domain.configs.validator.Validator;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.User;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.usersservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.usersservice.domain.user.dto.validator.CreateUpdateUserDtoValidator;
import com.salescompany.usersservice.domain.user.repository.UserRepository;
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
    public CreateUserResponseDto createUserCustomer(CreateUpdateUserDto createUpdateUserDto) {

        var userToInsert = createUserToInsert(createUpdateUserDto, Role.USER_CUSTOMER);

        var insertedUser = userRepository.add(userToInsert)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UsersServiceException("cannot insert user"));

     //   publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

        return insertedUser;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createUserManager(CreateUpdateUserDto createUpdateUserDto) {

        var userToInsert = createUserToInsert(createUpdateUserDto, Role.USER_MANAGER);

        var insertedUser = userRepository.add(userToInsert)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UsersServiceException("cannot insert user"));

        publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

        return insertedUser;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createAdminProduct(CreateUpdateUserDto createUpdateUserDto) {

        var userToInsert = createUserToInsert(createUpdateUserDto, Role.ADMIN_PRODUCT);

        var insertedUser = userRepository.add(userToInsert)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UsersServiceException("cannot insert user"));

        publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

        return insertedUser;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createAdminShop(CreateUpdateUserDto createUpdateUserDto) {

        var userToInsert = createUserToInsert(createUpdateUserDto, Role.ADMIN_SHOP);

        var insertedUser = userRepository.add(userToInsert)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UsersServiceException("cannot insert user"));

        publisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());

        return insertedUser;
    }


    private User createUserToInsert(CreateUpdateUserDto createUpdateUserDto, Role role) {
        Validator.validate(new CreateUpdateUserDtoValidator(), createUpdateUserDto);

        return createUpdateUserDto.toUser()
                .withPassword(passwordEncoder.encode(createUpdateUserDto.getPassword()))
                .withCreationDateTime(LocalDateTime.now())
                .withRole(role);
    }


}
