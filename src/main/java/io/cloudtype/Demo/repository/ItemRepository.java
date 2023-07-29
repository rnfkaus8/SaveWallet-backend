package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
