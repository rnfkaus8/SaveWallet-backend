package io.cloudtype.Demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class MemberResponse {
    private Long id;
    private String deviceId;
}
