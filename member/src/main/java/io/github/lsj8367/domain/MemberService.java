package io.github.lsj8367.domain;

import java.util.List;

public interface MemberService {
    List<Member> findAll();

    Member findById(final Long id);

    Member save(final MemberCreateRequest request);
}
