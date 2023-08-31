package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ItemResponse {

    private Long id;

    private String name;

    private int price;

    private LocalDateTime boughtDate;

}
