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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void updateItem(String name, int price, LocalDateTime boughtDate) {
        this.name = name;
        this.price = price;
        this.boughtDate = boughtDate;
    }



}