package me.tmdtjq32.myproject.src.model.mapper;

import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.model.entity.Study;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyMapperTest {

    @Test
    void toStudyResDTOTest(){
        Study study = Study.builder().idx(1L).name("study").chapter(1).status(StudyStatus.TODO).build();
        StudyResDTO resDTO = StudyResDTO.builder().idx(1L).name("study").limit(1).status(StudyStatus.TODO).build();

        StudyResDTO result = StudyMapper.INSTANCE.toStudyResDTO(study);

        assertAll(
                () -> assertThat(result.getIdx()).as("idx가 다릅니다").isEqualTo(resDTO.getIdx()),
                () -> assertThat(result.getName()).as("name 다릅니다").isEqualTo(resDTO.getName()),
                () -> assertThat(result.getStatus()).as("status 다릅니다").isEqualTo(resDTO.getStatus()),
                () -> assertThat(result.getLimit()).as("limit가 다릅니다").isEqualTo(resDTO.getLimit())
        );
    }

}