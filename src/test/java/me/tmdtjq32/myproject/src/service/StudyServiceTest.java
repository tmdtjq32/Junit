package me.tmdtjq32.myproject.src.service;

import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;
import me.tmdtjq32.myproject.src.model.mapper.StudyMapper;
import me.tmdtjq32.myproject.src.repository.MemberRepository;
import me.tmdtjq32.myproject.src.repository.StudyRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
class StudyServiceTest {

    @InjectMocks
    StudyService studyService;
    @Mock
    MemberRepository memberRepository;
    @Autowired
    StudyRepository studyRepository;
    @Mock
    StudyMapper studyMapper;

    @Container
    static DockerComposeContainer composeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    @Test
    @DisplayName("createStudy 성공 케이스")
    void createStudy() throws Exception {
        // given
        StudyReqDTO reqDTO = StudyReqDTO.builder()
                .limit(1)
                .name("study")
                .status(StudyStatus.TODO)
                .owner(1L)
                .build();

        Study study = Study.builder()
                .idx(1L)
                .chapter(1)
                .name("study")
                .status(StudyStatus.TODO)
                .owner(1L)
                .build();

        StudyResDTO expected = StudyResDTO.builder()
                .limit(reqDTO.getLimit())
                .name(reqDTO.getName())
                .status(reqDTO.getStatus())
                .build();

        Member member = Member.builder()
                .idx(1L)
                .name("test")
                .email("test@email.com")
                .build();

        when(studyMapper.toStudy(reqDTO))
                .thenReturn(study);

        when(studyMapper.toStudyResDTO(study))
                .thenReturn(expected);

        when(memberRepository.findById(reqDTO.getOwner()))
                .thenReturn(Optional.of(member));

        // when
        StudyResDTO result = studyService.createStudy(reqDTO);

        // then
        assertAll(
                () -> assertThat(result.getLimit()).as("limit가 일치하지 않습니다").isEqualTo(expected.getLimit()),
                () -> assertThat(result.getName()).as("name이 일치하지 않습니다").isEqualTo(expected.getName()),
                () -> assertThat(result.getStatus()).as("status가 일치하지 않습니다").isEqualTo(expected.getStatus())
        );
    }


    @DisplayName("createStudy Member가 없을 때")
    void createStudyMemberNotExist() throws Exception {
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
                .owner(reqDTO.getOwner())
                .build();

        when(memberRepository.findById(reqDTO.getOwner())) // Member가 없다면
                .thenReturn(Optional.ofNullable(null));
        // when
        StudyResDTO result = studyService.createStudy(reqDTO);

        // then
        assertAll(
                () -> verify(memberRepository)
                        .findById(reqDTO.getOwner()),
                () -> verify(studyRepository,never().description("위에서 예외를 던지므로 save 메소드는 실행되지 않아야 함"))
                        .save(study),
                () -> assertThat(result).as("예외를 던지지 않음").isNull()
        );
    }
}