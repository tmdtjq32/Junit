package me.tmdtjq32.myproject.src.service;

import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;
import me.tmdtjq32.myproject.src.model.mapper.StudyMapper;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import me.tmdtjq32.myproject.src.repository.StudyRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @InjectMocks
    StudyService studyService;

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Mock
    StudyMapper studyMapper;

    @Test
    @DisplayName("createStudy 성공 케이스")
    void createStudy() {
        // given
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

        Member member = Member.builder()
                .idx(1L)
                .name("test")
                .email("test@email.com")
                .build();

        when(memberService.findById(reqDTO.getOwner()))
                .thenReturn(Optional.of(member));

        when(studyMapper.toStudy(reqDTO))
                .thenReturn(study);

        when(studyRepository.save(study))
                .thenReturn(study);

        when(studyMapper.toStudyResDTO(study))
                .thenReturn(resDTO);
        // when
        StudyResDTO result = studyService.createStudy(reqDTO);

        // then
        assertAll(
                () -> assertThat(result.getLimit()).as("limit가 일치하지 않습니다").isEqualTo(resDTO.getLimit()),
                () -> assertThat(result.getName()).as("name이 일치하지 않습니다").isEqualTo(resDTO.getName()),
                () -> assertThat(result.getStatus()).as("status가 일치하지 않습니다").isEqualTo(resDTO.getStatus())
        );
    }

    @Test
    @DisplayName("createStudy Member가 없을 때")
    void createStudyMemberNotExist() {
        // given
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

        when(memberService.findById(reqDTO.getOwner())) // Member가 없다면
                .thenReturn(Optional.ofNullable(null));
        // when
        StudyResDTO result = studyService.createStudy(reqDTO);

        // then
        assertAll(
                () -> verify(memberService)
                        .findById(reqDTO.getOwner()),
                () -> verify(studyRepository,never().description("위에서 예외를 던지므로 save 메소드는 실행되지 않아야 함"))
                        .save(study),
                () -> assertThat(result).as("예외를 던지지 않음").isNull()
        );
    }
}