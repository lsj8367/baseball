package io.github.lsj8367.application;

import io.github.lsj8367.domain.Member;
import io.github.lsj8367.domain.MemberCreateRequest;
import io.github.lsj8367.domain.MemberResponse;
import io.github.lsj8367.domain.MemberResponseGroup;
import io.github.lsj8367.domain.MemberService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public record MemberFacade(MemberService memberService) {

    public MemberResponseGroup findAll() {
        final List<Member> memberList = memberService.findAll();
        return new MemberResponseGroup(memberList.stream()
            .map(MemberResponse::new)
            .toList());
    }

    public MemberResponse findById(final Long id) {
        return new MemberResponse(memberService.findById(id));
    }

    public MemberResponse save(final MemberCreateRequest request) {
        return new MemberResponse(memberService.save(request));
    }
}
