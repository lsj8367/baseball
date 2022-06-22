package io.github.lsj8367.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLoginCheckerTest {

    private final UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    @DisplayName("잠겨있는 사용자와 input UserId가 다른경우")
    void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", LocalDateTime.now()));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID"), new LockUser("TEST_USER_ID", LocalDateTime.now()));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
            "TEST_USER_ID"), new LockUser("TEST_USER_ID", LocalDateTime.now()));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", threeHoursBefore()));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", threeHoursBefore()));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private LocalDateTime threeHoursBefore() {
        return LocalDateTime.now().minusHours(3L);
    }

}
