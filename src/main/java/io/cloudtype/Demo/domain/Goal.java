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
public class Goal extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String targetMonth;

    private int goalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void updatePrice(int price) {
        this.goalPrice = price;
    }
}