package io.github.lsj8367.c.refactoring.menuexamples;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuItemTest {

    @Test
    @DisplayName("MenuItem 의 role 과 Role 의 name 이 read 로 같다면 READ 반환")
    void sameReadAccessThenChangeAccess() {
        final MenuItem menuItem = new MenuItem("name", "MenuARead", "MenuAWrite");
        final Role[] roles = {new Role("MenuARead")};
        menuItem.setAccessMenuItem(roles);

        assertMenuItem(menuItem, "READ");
    }

    @Test
    @DisplayName("MenuItem 의 role 과 Role 의 name 이 Write 로 같다면 READ 반환")
    void sameWriteAccessThenChangeAccess() {
        final MenuItem menuItem = new MenuItem("name", "MenuARead", "MenuAWrite");
        final Role[] roles = {new Role("MenuAWrite")};
        menuItem.setAccessMenuItem(roles);

        assertMenuItem(menuItem, "WRITE");
    }

    private void assertMenuItem(final MenuItem menuItem, final String access) {
        assertThat(menuItem.getAccess()).isEqualTo(access);
        assertThat(menuItem.isVisible()).isTrue();
    }

}