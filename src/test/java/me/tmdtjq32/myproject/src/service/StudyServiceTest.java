package me.tmdtjq32.myproject.src.service;

import me.tmdtjq32.myproject.src.model.entity.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

class StudyServiceTest {

    @Test
    @DisplayName("createStudy 테스트")
    void createStudy(){
        Study study = Study.builder().build();

        assertNotNull(study);
    }
}