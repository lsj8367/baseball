package io.github.lsj8367.infrastructure;

import io.github.lsj8367.domain.Member;
import io.github.lsj8367.domain.MemberReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(RuntimeException::new);
    }

}
