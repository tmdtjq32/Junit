package me.tmdtjq32.myproject.src.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.mapper.StudyMapper;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import me.tmdtjq32.myproject.src.repository.StudyRepository;
import me.tmdtjq32.myproject.src.model.entity.Study;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudyService {
    @NonNull
    private final MemberRepository memberRepository;
    @NonNull
    private final StudyRepository studyRepository;
    @NonNull
    private final StudyMapper studyMapper;

    public StudyResDTO createStudy(StudyReqDTO reqDTO) {
        Optional<Member> member = memberRepository.findById(reqDTO.getOwner().getIdx());

        Study study = Study.builder().build();

        study.setOwner(member.orElseThrow(
                () -> new IllegalArgumentException("Member is doesn`t exists id :" + member.get().getIdx())
        ));

        studyRepository.save(study);

        return studyMapper.toStudyResDTO(study);
    }
}
