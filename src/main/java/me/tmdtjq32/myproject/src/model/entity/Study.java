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
    private Long idx;

    @Column
    private StudyStatus status;

    @Column
    private Integer limit;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;


}
