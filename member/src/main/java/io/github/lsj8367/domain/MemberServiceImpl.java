package io.github.lsj8367.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberReader memberReader;

    private final MemberStore memberStore;

    @Override
    public List<Member> findAll() {
        return memberReader.findAll();
    }

    @Override
    public Member findById(final Long id) {
        return memberReader.findById(id);
    }

    @Override
    public Member save(final MemberCreateRequest request) {
        return memberStore.save(request);
    }

}
