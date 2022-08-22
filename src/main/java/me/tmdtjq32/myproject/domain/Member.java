package me.tmdtjq32.myproject.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long idx;
    private String name;
    private String email;
}
