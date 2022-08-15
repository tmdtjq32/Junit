package me.tmdtjq32.myproject;


import lombok.*;

import java.util.Random;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {
    private StudyStatus status;
    private Integer limit;
    private String name;
    private Random random;

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                ", random=" + random +
                ", hashCode=" + hashCode() +
                '}';
    }
}
