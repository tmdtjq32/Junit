package me.tmdtjq32.myproject.src.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyResDTO {
    private Long idx;
    private String status;
    private Integer limit;
    private String name;
}
