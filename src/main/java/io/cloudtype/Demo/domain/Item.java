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
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private LocalDateTime boughtDate;

    @ManyToOne
    private Member member;



}