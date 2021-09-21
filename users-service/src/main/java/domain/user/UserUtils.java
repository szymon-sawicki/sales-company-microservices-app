package domain.user;

import java.util.function.Function;

public interface UserUtils {
    Function<User,Long> toId = user -> user.id;
}
