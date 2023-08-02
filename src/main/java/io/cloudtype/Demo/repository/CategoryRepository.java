package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
