package com.salescompany.userservice.infrastructure.controller;

import com.salescompany.userservice.application.service.UsersService;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.userservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.salescompany.userservice.infrastructure.controller.dto.ResponseDataDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/customer_registration")
    public ResponseDataDto<CreateUserResponseDto> customerRegistration(@RequestBody CreateUpdateUserDto createUpdateUserDto) {
        return toResponse(usersService.createUser(createUpdateUserDto, Role.USER_CUSTOMER));
    }

    @GetMapping("/mail/{mail}")
    public ResponseDataDto<Getcd..UserDto> findByMail(@PathVariable String mail) {
        return toResponse(usersService.findByMail(mail));
    }

    @GetMapping("/username/{username}")
    public ResponseDataDto<GetUserDto> findByUsername(@PathVariable String username) {
        return toResponse(usersService.findByUsername(username));
    }

    @GetMapping("/{id}")
    public ResponseDataDto<GetUserDto> findById(@PathVariable Long id) {
        return toResponse(usersService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseDataDto<GetUserDto> update(@PathVariable Long id, @RequestBody CreateUpdateUserDto createUpdateUserDto) {
        return toResponse(usersService.update(id,createUpdateUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseDataDto<GetUserDto> delete(@PathVariable Long id) {
        return toResponse(usersService.delete(id));
    }

    @GetMapping("/activation")
    public ResponseDataDto<CreateUserResponseDto> activate(@RequestParam("token") String token) {
        return toResponse(usersService.activateUser(token));
    }

}
