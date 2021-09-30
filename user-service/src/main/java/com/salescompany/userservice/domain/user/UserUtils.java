package com.salescompany.userservice.domain.user;

import com.salescompany.userservice.domain.user.Type.Role;

import java.time.LocalDateTime;
import java.util.function.Function;

public interface UserUtils {
    Function<User,Long> toId = user -> user.id;
    Function<User, Role> toRole = user -> user.role;
    Function<User, LocalDateTime> toCreationDateTime = user -> user.creationDateTime;
}
