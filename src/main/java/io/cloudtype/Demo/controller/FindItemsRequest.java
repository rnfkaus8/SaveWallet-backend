package io.cloudtype.Demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class FindItemsRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long memberId;
}
