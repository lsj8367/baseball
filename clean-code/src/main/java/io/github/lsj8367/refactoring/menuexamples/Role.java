package io.github.lsj8367.refactoring.menuexamples;

public class Role {

    private final String name;

    public Role(String name) {
        this.name = name;
    }

    public boolean isEqualTo(final String name) {
        return this.name.equals(name);
    }

}
