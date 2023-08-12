package io.cloudtype.Demo.controller.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class SaveItemRequest {
    private String name;
    private int price;
    private Long memberId;
    private LocalDateTime boughtDate;
}
