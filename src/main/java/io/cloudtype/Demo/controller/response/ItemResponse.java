package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Data
public class ItemResponse {

    private Long id;

    private String name;

    private int price;

    private LocalDateTime boughtDate;

}
