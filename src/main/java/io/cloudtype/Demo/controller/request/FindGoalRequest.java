package io.cloudtype.Demo.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindGoalRequest {
    private String targetMonth;
    private Long memberId;
}
