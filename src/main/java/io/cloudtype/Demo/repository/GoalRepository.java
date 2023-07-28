package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
