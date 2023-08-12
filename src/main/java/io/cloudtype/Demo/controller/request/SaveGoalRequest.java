package io.cloudtype.Demo.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveGoalRequest {

    private String targetMonth;
    private Long memberId;
    private int goalPrice;
}
