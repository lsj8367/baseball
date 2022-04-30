package io.github.lsj8367.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class MemberResponse {
    private final Long id;
    private final String email;
    private final String name;

    public MemberResponse(final Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
    }

}
