package me.tmdtjq32.myproject.src.model.DTO;

import lombok.*;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyReqDTO {
    private StudyStatus status;
    private Integer limit;
    private String name;
    private Long owner;
}
