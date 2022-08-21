package me.tmdtjq32.myproject.domain;


import lombok.*;
import me.tmdtjq32.myproject.study.StudyStatus;

import java.util.Random;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {
    private StudyStatus status;
    private Integer limit;
    private String name;
    private Random random;
    private Member owner;


}
