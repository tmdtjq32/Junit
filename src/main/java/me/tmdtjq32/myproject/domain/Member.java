package me.tmdtjq32.myproject.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Integer idx;
    private String name;
}
