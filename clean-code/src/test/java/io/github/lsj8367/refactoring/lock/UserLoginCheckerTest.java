package io.github.lsj8367.refactoring.lock;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLoginCheckerTest {

    private final UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    @DisplayName("잠겨있는 사용자와 input UserId가 다른경우")
    void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        final Lock actual = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", LocalDateTime.now()));
        final Lock expected = Lock.readLockWithMessage("Locked by TEST_USER_ID_1", true);
        assertLock(actual, expected);
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Lock actual = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID"), new LockUser("TEST_USER_ID", LocalDateTime.now()));
        final Lock expected = Lock.writeLock(false);
        assertLock(actual, expected);
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        final Lock actual = userLoginChecker.isUserAllowedToLogin(false, new User(
            "TEST_USER_ID"), new LockUser("TEST_USER_ID", LocalDateTime.now()));
        final Lock expected = Lock.writeLock(false);
        assertLock(actual, expected);
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        final Lock actual = userLoginChecker.isUserAllowedToLogin(true, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", threeHoursBefore()));
        final Lock expected = Lock.writeLock(false);
        assertLock(actual, expected);
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        final Lock actual = userLoginChecker.isUserAllowedToLogin(false, new User(
            "TEST_USER_ID_2"), new LockUser("TEST_USER_ID_1", threeHoursBefore()));
        final Lock expected = Lock.readLockWithMessage("Locked by TEST_USER_ID_1", true);
        assertLock(actual, expected);
    }

    private void assertLock(final Lock actual, final Lock expected) {
        assertThat(actual).isEqualTo(expected);
    }

    private LocalDateTime threeHoursBefore() {
        return LocalDateTime.now().minusHours(3L);
    }

}
