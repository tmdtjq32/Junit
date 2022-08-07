package me.tmdtjq32.myproject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private Integer limit = 0;
}
