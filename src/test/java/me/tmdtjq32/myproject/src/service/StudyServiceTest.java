package me.tmdtjq32.myproject.src.service;

import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;
import me.tmdtjq32.myproject.src.model.mapper.StudyMapper;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import me.tmdtjq32.myproject.src.repository.StudyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @InjectMocks
    StudyService studyService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    StudyRepository studyRepository;

    @Mock
    StudyMapper studyMapper;

    @Test
    @DisplayName("createStudy Member ID Null check")
    void createStudy() {
        StudyReqDTO reqDTO = StudyReqDTO.builder()
                .limit(1)
                .name("study")
                .status(StudyStatus.TODO)
                .owner(1L)
                .build();

        Study study = Study.builder()
                .chapter(reqDTO.getLimit())
                .name(reqDTO.getName())
                .status(reqDTO.getStatus())
                .owner(Member.builder().idx(reqDTO.getOwner()).build())
                .build();

        StudyResDTO resDTO = StudyResDTO.builder()
                .limit(study.getChapter())
                .name(study.getName())
                .status(study.getStatus())
                .build();

        when(memberRepository.findById(reqDTO.getOwner()))
                .thenReturn(Optional.of(Member.builder()
                        .idx(1L)
                        .name("test")
                        .email("test@email.com")
                        .build()));

        when(studyMapper.toStudy(reqDTO))
                .thenReturn(study);

        when(studyRepository.save(study))
                .thenReturn(study);

        when(studyMapper.toStudyResDTO(study))
                .thenReturn(resDTO);

        StudyResDTO result = studyService.createStudy(reqDTO);

        assertAll(
                () -> assertThat(result.getLimit()).isEqualTo(resDTO.getLimit()),
                () -> assertThat(result.getName()).isEqualTo(resDTO.getName()),
                () -> assertThat(result.getStatus()).isEqualTo(resDTO.getStatus())
        );


    }
}