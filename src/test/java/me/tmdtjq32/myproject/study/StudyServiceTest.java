package me.tmdtjq32.myproject.study;

import me.tmdtjq32.myproject.domain.Member;
import me.tmdtjq32.myproject.domain.Study;
import me.tmdtjq32.myproject.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() {
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertThat(studyService)
                .isNotNull();

        Member member = Member.builder()
                .idx(1L)
                .name("test")
                .email("test@email.com")
                .build();

        Study study = Study.builder()
                .idx(1L)
                .name("member")
                .limit(10)
                .build();

        when(memberService.findById(1L))
                .thenReturn(Optional.of(member));

        studyService.createStudy(1L,study);

        assertThat(member)
                .isEqualTo(study.getOwner());

        verify(memberService, times(1)).notify(study);


    }

}