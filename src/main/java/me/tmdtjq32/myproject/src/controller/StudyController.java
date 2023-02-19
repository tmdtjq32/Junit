package me.tmdtjq32.myproject.src.controller;

import lombok.RequiredArgsConstructor;
import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.service.StudyService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StudyController {

    private final StudyService studyService;

    @ResponseBody
    @PostMapping("/study")
    public StudyResDTO createStudy(@RequestBody StudyReqDTO reqDTO) {
        return studyService.createStudy(reqDTO);
    }

}
