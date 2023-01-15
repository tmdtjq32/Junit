package me.tmdtjq32.myproject.src.model.DTO;

import lombok.Data;
import me.tmdtjq32.myproject.src.model.entity.Member;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;

@Data
public class StudyResDTO {
    private Long idx;
    private StudyStatus status;
    private Integer limit;
    private String name;
    private Member owner;
}
