package io.github.lsj8367.c.refactoring.menuexamples;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuAccessTest {

    private final Role[] readRoles = {new Role("MenuARead")};
    private final Role[] writeRoles = {new Role("MenuAWrite")};
    private List<MenuItem> menuItems;

    @BeforeEach
    void setUp() {
        menuItems = List.of(
            new MenuItem("A", "MenuARead", "MenuAWrite")
        );
    }

    @Test
    @DisplayName("권한이 없으면 null")
    void roleIsNullEarlyReturn() {
        final MenuAccess menuAccess = new MenuAccess(menuItems);
        menuAccess.setAuthorizationsInEachMenus(null);
        assertThat(menuItems.get(0).getAccess()).isNull();
        assertThat(menuItems.get(0).isVisible()).isFalse();
    }

    @Test
    @DisplayName("Read 권한이 서로 같으면 정상적으로 변환")
    void sameReadRoleThenChangeString() {
        //given
        final MenuAccess menuAccess = new MenuAccess(menuItems);

        //when
        menuAccess.setAuthorizationsInEachMenus(readRoles);

        //then
        assertMenuItemRead(menuItems.get(0));
    }

    @Test
    @DisplayName("Read 권한이 서로 같으면 정상적으로 변환")
    void sameWriteRoleThenChangeString() {
        //given
        final MenuAccess menuAccess = new MenuAccess(menuItems);

        //when
        menuAccess.setAuthorizationsInEachMenus(writeRoles);

        //then
        assertMenuItemWrite(menuItems.get(0));
    }

    private void assertMenuItemRead(final MenuItem menuItem) {
        assertThat(menuItem.getAccess()).isEqualTo(Constants.READ);
        assertThat(menuItem.isVisible()).isTrue();
    }

    private void assertMenuItemWrite(final MenuItem menuItem) {
        assertThat(menuItem.getAccess()).isEqualTo(Constants.WRITE);
        assertThat(menuItem.isVisible()).isTrue();
    }

}
