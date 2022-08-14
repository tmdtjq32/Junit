package me.tmdtjq32.myproject;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Study_Test {

    @FastTest
    @DisplayName("fast test")
    void get_Status(){
        Study study = new Study();
        assertThat(study.getStatus())
                .as(()-> "chk StudyStatus")
                .isEqualTo(StudyStatus.DRAFT);
    }

    @SlowTest
    @DisplayName("slow test")
    void create_new_study() {
        Study study = new Study();

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