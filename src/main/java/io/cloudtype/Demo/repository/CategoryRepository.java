package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.member.id = :memberId")
    List<Category> findByMemberId(@Param("memberId") Long id);
}
