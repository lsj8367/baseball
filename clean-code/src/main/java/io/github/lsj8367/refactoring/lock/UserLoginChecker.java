package io.github.lsj8367.refactoring.lock;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class UserLoginChecker {

    public static final long LOCK_FOR_THREE_HOURS = 1L;

    public Lock isUserAllowedToLogin(boolean isFirstScreen, User loginUserRequest, final LockUser existingUsers) {
        if (Objects.isNull(existingUsers)) {
            return writeLock();
        }

        final String lockUserId = existingUsers.lockUserId();
        final LocalDateTime lockTimestamp = existingUsers.lockTimeStamp();

        if (Objects.isNull(lockUserId)) {
            return writeLock();
        }

        if (lockUserId.equalsIgnoreCase(loginUserRequest.getUserId())) {
            return writeLock();
        }

        final long timeDifference = ChronoUnit.HOURS.between(lockTimestamp, LocalDateTime.now());
        if (isFirstScreen && timeDifference > LOCK_FOR_THREE_HOURS) {
            return writeLock();
        }

        return readLockWithMessage(lockUserId);
    }

    private Lock readLockWithMessage(final String lockUserId) {
        // firstScreen이 아니며, 문자열이 서로 틀린경우
        // 유저의 아이디와 입력받는 id가 다른경우 이부분을 수행함

        // 사용자한테 표시되는 메시지
        final String lockMsg = Constants.LOCK_TEXT.replace("@@USER@@", lockUserId);
        return Lock.readLockWithMessage(lockMsg, true);
    }

    private Lock writeLock() {
        // Locked By Same User, Write access
        return Lock.writeLock(false);
    }

}
