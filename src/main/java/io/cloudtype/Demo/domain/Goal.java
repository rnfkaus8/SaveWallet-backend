package io.cloudtype.Demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goal")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime createdAt;

    private String targetMonth;

    private int goalPrice;

    @ManyToOne
    private Member member;
}