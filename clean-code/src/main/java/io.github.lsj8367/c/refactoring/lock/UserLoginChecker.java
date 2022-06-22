package io.github.lsj8367.c.refactoring.lock;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserLoginChecker {

    public static final int LOCK_FOR_THREE_HOURS = 3600000;

    public Lock isUserAllowedToLogin(long id, String status,
        boolean isFirstScreen, User loginUserRequest, List<Object> existingUsers) {
        if (existingUsers.isEmpty()) {
            return writeLock();
        }

        Object[] existingUser = (Object[]) existingUsers.get(0);
        String lockUserId = (String) existingUser[0];
        Date lockTimestamp = (Date) existingUser[1];

        if (Objects.isNull(lockUserId)) {
            return writeLock();
        }

        if (lockUserId.equalsIgnoreCase(loginUserRequest.getUserId())) {
            return writeLock();
        }

        //if userID is present, the Lock time stamp will also be present
        //4800000 milliseconds equals to 1.5 hours.
        // 3600000은 1시간
        if (isFirstScreen && new Date().getTime() - lockTimestamp.getTime() > LOCK_FOR_THREE_HOURS) {
            return writeLock();
        }

        return readLockWithMessage(lockUserId);
    }

    private Lock readLockWithMessage(final String lockUserId) {
        // firstScreen이 아니며, 문자열이 서로 틀린경우
        // 유저의 아이디와 입력받는 id가 다른경우 이부분을 수행함

        // 사용자한테 표시되는 메시지
        final String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", lockUserId);
        final Lock lock = new Lock();
        lock.setRead(true);
        // 읽기 잠금
        lock.setLockReason(lockMsg);
        return lock;
    }

    private Lock writeLock() {
        // Locked By Same User, Write access
        final Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }

}
