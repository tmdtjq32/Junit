package me.tmdtjq32.myproject.member;

import me.tmdtjq32.myproject.domain.Member;
import me.tmdtjq32.myproject.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId) throws IllegalArgumentException;

    void notify(Study study);
}
