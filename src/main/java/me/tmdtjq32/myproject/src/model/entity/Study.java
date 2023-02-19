package me.tmdtjq32.myproject.src.model.entity;


import lombok.*;
import me.tmdtjq32.myproject.src.model.enums.StudyStatus;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long idx;

    @Column
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    @Column
    private Integer chapter;

    @Column
    private String name;

    @Column
    private Long owner;


}
