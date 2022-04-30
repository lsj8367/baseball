package io.github.lsj8367.domain;

public record MemberCreateRequest(String email, String name, String password) {

    public Member toEntity() {
        return Member.builder()
            .email(email)
            .name(name)
            .password(password)
            .build();
    }
}
