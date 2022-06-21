package io.github.lsj8367.c.refactoring.lock;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserLoginChecker {

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status,
        boolean isFirstScreen, User user, List<Object> list) {
        if (list.isEmpty()) {
            Lock lock = new Lock();
            lock.setRead(false);
            return lock;
        }

        Object[] object = (Object[]) list.get(0);
        String lockUserId = (String) object[0];
        Date lockTimestamp = (Date) object[1];
        if (Objects.isNull(lockUserId)) {
            Lock lock = new Lock();
            lock.setRead(false);
            return lock;
        }
        // message which is shown to the user
        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@",
            lockUserId);
        //if userID is present, the Lock time stamp will also be present
        //4800000 milliseconds equals to 1.5 hours.
        // 3600000은 1시간
        Date time = new Date();
        if (time.getTime() - lockTimestamp.getTime() > 3600000) {
            //New user gets lock only on first screen
            //If 1 1/2 hours expires when user is not on 1st screen then for same user lock can be refreshed.
            if (isFirstScreen || lockUserId.equalsIgnoreCase(user.getUserId())) {
                //to set the  access to write mode
                final Lock lock = new Lock();
                lock.setRead(false);
                return lock;
            }
        }

        if (lockUserId.equalsIgnoreCase(user.getUserId())) {
            final Lock lock = new Lock();
            // Locked By Same User, Write access
            lock.setRead(false);
            return lock;
        }

        Lock lock = new Lock();
        // firstScreen이 아니며, 문자열이 서로 틀린경우
        // 유저의 아이디와 입력받는 id가 다른경우 이부분을 수행함
        lock.setRead(true);
        //Only Read Access is Permitted
        lock.setLockReason(lockMsg);
        return lock;
    }

}
