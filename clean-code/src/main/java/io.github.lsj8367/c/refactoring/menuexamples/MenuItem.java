package io.github.lsj8367.c.refactoring.menuexamples;

import java.util.Arrays;

public class MenuItem {

    private final String name;
    private final String readAccessRole;
    private final String writeAccessRole;
    private String access;
    private boolean visible;

    public MenuItem(final String name, final String readAccessRole, final String writeAccessRole) {
        this.name = name;
        this.readAccessRole = readAccessRole;
        this.writeAccessRole = writeAccessRole;
    }

    public String getAccess() {
        return access;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setAccessMenuItem(final Role[] roles) {
        if (isReadOrWriteHaveTheRole(roles, readAccessRole)) {
            access = Constants.READ;
            visible = true;
            return;
        }

        access = Constants.WRITE;
        visible = true;
    }

    private boolean isReadOrWriteHaveTheRole(final Role[] roles, final String accessRole) {
        return Arrays.stream(roles)
            .anyMatch(role -> role.isEqualTo(accessRole));
    }

}
