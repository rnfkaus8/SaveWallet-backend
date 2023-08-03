package io.cloudtype.Demo.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateCategoryRequest {
    private String name;
    private Long categoryId;
}
