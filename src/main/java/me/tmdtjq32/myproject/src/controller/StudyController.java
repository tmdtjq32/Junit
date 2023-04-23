package me.tmdtjq32.myproject.src.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tmdtjq32.myproject.src.model.DTO.StudyReqDTO;
import me.tmdtjq32.myproject.src.model.DTO.StudyResDTO;
import me.tmdtjq32.myproject.src.service.StudyService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StudyController {

    private final StudyService studyService;

    @ResponseBody
    @GetMapping("/study/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public StudyResDTO findStudy(@PathVariable("id") Long id) throws RuntimeException{
        return studyService.findStudy(id);
    }

    public StudyResDTO fallbackMethod(Long id, Throwable throwable){
        log.error(throwable.getMessage());
        return null;
    }

    @ResponseBody
    @PostMapping("/study")
    public StudyResDTO createStudy(@RequestBody StudyReqDTO reqDTO) {
        return studyService.createStudy(reqDTO);
    }

}
