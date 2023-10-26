package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class GoalResponse {
    private Long id;

    private String targetMonth;

    private int goalPrice;
}
