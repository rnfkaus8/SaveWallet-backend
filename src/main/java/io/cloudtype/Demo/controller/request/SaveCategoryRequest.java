package io.cloudtype.Demo.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveCategoryRequest {
    private String name;
    private Long memberId;
}
