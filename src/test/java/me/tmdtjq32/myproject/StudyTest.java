package me.tmdtjq32.myproject;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study();
//        study.setStatus(StudyStatus.STARTED);
//        study.setLimit(11);
        assertAll(
                () -> assertThat(study)
                        .as(() -> "study is not null")
                        .isNotNull(),
                () -> assertThat(study.getStatus())
                .as(() -> "status is not DRAFT")
                .isEqualTo(StudyStatus.DRAFT),
                () -> assertThat(study.getLimit())
                .as(() -> "limit is not over 10")
                .isLessThan(10)
        );
    }

}