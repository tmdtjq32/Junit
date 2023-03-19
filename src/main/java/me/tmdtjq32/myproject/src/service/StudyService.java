package me.tmdtjq32.myproject.src.service;

import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.exception.APIException;
import me.tmdtjq32.myproject.src.exception.BaseErrorCode;
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

    private final MemberRepository memberRepository;

    private final StudyRepository studyRepository;

    private final StudyMapper studyMapper;

    public StudyResDTO findStudy(Long id) {
        Optional<Study> study = studyRepository.findById(id);

        if (study.isEmpty()){
            throw new APIException(BaseErrorCode.NO_RESULT,"cause by studyId " + id);
        }

        return studyMapper.toStudyResDTO(study.get());
    }

    public StudyResDTO createStudy(StudyReqDTO reqDTO) {
        Optional<Member> member = memberRepository.findById(reqDTO.getOwner());
        if (member.isEmpty()){
            throw new APIException(BaseErrorCode.NO_RESULT,"cause by memberId " + reqDTO.getOwner());
        }

        Study study = studyMapper.toStudy(reqDTO);

        studyRepository.save(study);

        return studyMapper.toStudyResDTO(study);
    }
}
