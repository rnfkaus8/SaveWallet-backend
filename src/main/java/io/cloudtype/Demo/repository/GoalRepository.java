package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("select g from Goal g where g.targetMonth = :targetMonth and g.member.id = :memberId")
    Optional<Goal> findTargetMonthGoal(@Param("targetMonth") String targetMonth, @Param("memberId") Long memberId);
}
