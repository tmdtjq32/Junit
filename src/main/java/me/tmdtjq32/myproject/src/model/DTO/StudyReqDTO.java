package me.tmdtjq32.myproject.src.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class StudyReqDTO {
    private StudyStatus status;
    private Integer limit;
    private String name;
    private Long owner;
}
