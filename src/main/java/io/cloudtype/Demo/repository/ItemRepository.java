package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
