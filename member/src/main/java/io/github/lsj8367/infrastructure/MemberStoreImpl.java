package io.github.lsj8367.infrastructure;

import io.github.lsj8367.domain.Member;
import io.github.lsj8367.domain.MemberCreateRequest;
import io.github.lsj8367.domain.MemberStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStoreImpl implements MemberStore {

    private final MemberRepository memberRepository;

    @Override
    public Member save(final MemberCreateRequest request) {
        return memberRepository.save(request.toEntity());
    }

}
