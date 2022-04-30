package io.github.lsj8367.domain;

public interface MemberStore {

    Member save(final MemberCreateRequest request);
}
