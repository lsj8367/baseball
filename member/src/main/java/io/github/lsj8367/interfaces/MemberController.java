package io.github.lsj8367.interfaces;

import io.github.lsj8367.application.MemberFacade;
import io.github.lsj8367.domain.MemberCreateRequest;
import io.github.lsj8367.domain.MemberResponse;
import io.github.lsj8367.domain.MemberResponseGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @GetMapping("/all")
    public ResponseEntity<MemberResponseGroup> findAll() {
        return ResponseEntity.ok(memberFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(memberFacade.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<MemberResponse> save(@RequestBody final MemberCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(memberFacade.save(request));
    }
}
