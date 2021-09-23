package com.salescompany.usersservice.infrastructure.controller;

import com.salescompany.usersservice.application.service.UsersService;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.usersservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.usersservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.salescompany.usersservice.infrastructure.controller.dto.ResponseDataDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/customer_registration")
    public ResponseDataDto<CreateUserResponseDto> customerRegistration(@RequestBody CreateUpdateUserDto createUpdateUserDto) {
        return  toResponse(usersService.createUser(createUpdateUserDto, Role.USER_CUSTOMER));
    }

    @GetMapping("/get")
    public ResponseDataDto<String> get() {
        return toResponse("test");
    }
}
