package me.tmdtjq32.myproject.src.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.exception.BaseException;
import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.mapper.StudyMapper;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import me.tmdtjq32.myproject.src.repository.StudyRepository;
import me.tmdtjq32.myproject.src.model.entity.Study;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
@Service
public class StudyService {

    @NonNull
    private final MemberRepository memberRepository;
    @NonNull
    private final StudyRepository studyRepository;
    @NonNull
    private final StudyMapper studyMapper;

    public StudyResDTO createStudy(StudyReqDTO reqDTO){
        Optional<Member> member = memberRepository.findById(reqDTO.getOwner());

        if (member.isEmpty()){
            throw new IllegalArgumentException("Member is doesn`t exists id :" + reqDTO.getOwner());
        }

        Study study = studyMapper.toStudy(reqDTO);

        studyRepository.save(study);

        return studyMapper.toStudyResDTO(study);
    }
}
