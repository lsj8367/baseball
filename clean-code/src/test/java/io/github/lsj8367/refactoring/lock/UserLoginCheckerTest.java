package io.github.lsj8367.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.lsj8367.refactoring.lock.Lock;
import io.github.lsj8367.refactoring.lock.User;
import io.github.lsj8367.refactoring.lock.UserLoginChecker;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserLoginCheckerTest {

    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    @DisplayName("잠겨있는 사용자와 input UserId가 다른경우")
    public void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] access = new Object[]{"TEST_USER_ID_1", LocalDateTime.now()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, new User(
            "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] access = new Object[]{"TEST_USER_ID", LocalDateTime.now()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, new User(
            "TEST_USER_ID"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] access = new Object[]{"TEST_USER_ID", LocalDateTime.now()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", false, new User(
            "TEST_USER_ID"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] access = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", true, new User(
            "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] access = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};
        Lock lock = userLoginChecker.isUserAllowedToLogin(10, "NOT_USED", false, new User(
            "TEST_USER_ID_2"), Arrays.asList(new Object[][]{access}));
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    public LocalDateTime threeHoursBefore() {
        return LocalDateTime.now().minusHours(3L);
    }

}
