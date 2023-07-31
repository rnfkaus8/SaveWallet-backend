package io.cloudtype.Demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private LocalDateTime createdAt;

    @ManyToOne
    private Member member;
}