package io.cloudtype.Demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
