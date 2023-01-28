package me.tmdtjq32.myproject.src.service;

import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(Long Id) {
        return memberRepository.findById(Id);
    }
}
