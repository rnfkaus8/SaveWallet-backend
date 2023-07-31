package io.cloudtype.Demo.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveItemRequest {
    private String name;
    private int price;
    private Long memberId;
}
