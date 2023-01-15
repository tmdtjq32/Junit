package me.tmdtjq32.myproject.src.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.service.StudyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudyController {

    @NonNull
    private final StudyService studyService;

    @PostMapping("/study")
    public StudyResDTO createStudy(@RequestBody StudyReqDTO reqDTO){
        return studyService.createStudy(reqDTO);
    }

}
