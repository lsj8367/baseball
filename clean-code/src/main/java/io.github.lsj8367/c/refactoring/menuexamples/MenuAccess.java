package io.github.lsj8367.c.refactoring.menuexamples;

import java.util.List;
import java.util.Objects;

public record MenuAccess(List<MenuItem> menuItemList) {

    public void setAuthorizationsInEachMenus(final Role[] roles) {
        if (Objects.isNull(roles)) {
            return;
        }

        for (MenuItem menuItem : menuItemList) {
            menuItem.setAccessMenuItem(roles);
        }
    }

}
