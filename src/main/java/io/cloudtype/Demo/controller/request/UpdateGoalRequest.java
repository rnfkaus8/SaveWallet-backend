package io.cloudtype.Demo.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateGoalRequest {
    private Long id;
    private int price;
}
