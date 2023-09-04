package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class TotalPriceByCategoryResponse {
    private String categoryName;
    private String color;
    private int totalPrice;
}
