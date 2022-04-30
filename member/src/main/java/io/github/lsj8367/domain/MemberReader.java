package io.github.lsj8367.domain;

import java.util.List;

public interface MemberReader {
    List<Member> findAll();

    Member findById(final Long id);
}
