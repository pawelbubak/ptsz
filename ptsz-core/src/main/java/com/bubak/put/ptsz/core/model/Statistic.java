package com.bubak.put.ptsz.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    private int instanceSize = 0;
    private int result = -1;
    private int expectedResult = -1;
    private boolean correct = false;
}
