package me.tmdtjq32.myproject.study;

import lombok.AllArgsConstructor;
import me.tmdtjq32.myproject.domain.Member;
import me.tmdtjq32.myproject.domain.Study;
import me.tmdtjq32.myproject.member.MemberService;

import java.util.Optional;

@AllArgsConstructor
public class StudyService {

    private final MemberService memberService;

    private final StudyRepository studyRepository;

    public Study createStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);

        study.setOwner(member.orElseThrow(
                () -> new IllegalArgumentException("Member is doesn`t exists id :" + memberId)
        ));
        return studyRepository.save(study);
    }

}
