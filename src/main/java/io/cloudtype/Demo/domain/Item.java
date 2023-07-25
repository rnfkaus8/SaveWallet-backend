package io.cloudtype.Demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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