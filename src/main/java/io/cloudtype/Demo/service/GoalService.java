package io.cloudtype.Demo.service;

import io.cloudtype.Demo.controller.request.UpdateGoalRequest;
import io.cloudtype.Demo.domain.Goal;
import io.cloudtype.Demo.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GoalService {
    private final GoalRepository goalRepository;

    @Transactional
    public void update(UpdateGoalRequest request) {
        Goal findGoal = goalRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목표입니다."));
        findGoal.updatePrice(request.getPrice());
    }
}
