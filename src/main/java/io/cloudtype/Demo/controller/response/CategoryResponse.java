package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
}
