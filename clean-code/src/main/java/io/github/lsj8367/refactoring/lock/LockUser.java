package io.github.lsj8367.refactoring.lock;

import java.time.LocalDateTime;

public record LockUser(String lockUserId, LocalDateTime lockTimeStamp) {

}
