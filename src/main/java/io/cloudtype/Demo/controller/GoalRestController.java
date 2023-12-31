package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.FindGoalRequest;
import io.cloudtype.Demo.controller.request.SaveGoalRequest;
import io.cloudtype.Demo.controller.request.UpdateGoalRequest;
import io.cloudtype.Demo.controller.response.GoalResponse;
import io.cloudtype.Demo.domain.Goal;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.GoalRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import io.cloudtype.Demo.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoalRestController {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final GoalService goalService;

    @PatchMapping("/goal")
    public ResponseEntity<String> update(@RequestBody UpdateGoalRequest request) {
        goalService.update(request);
        return ResponseEntity.ok().body("success");
    }

    @PutMapping("/goal")
    public ResponseEntity<GoalResponse> saveNotExistGoal(@RequestBody SaveGoalRequest request) {
        Optional<Goal> optionalGoal = goalRepository.findTargetMonthGoal(request.getTargetMonth(), request.getMemberId());
        if (optionalGoal.isPresent()) {
            Goal goal = optionalGoal.get();
            return ResponseEntity.ok().body(new GoalResponse(goal.getId(), goal.getTargetMonth(), goal.getGoalPrice()));
        }

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Goal goal = Goal.builder().goalPrice(request.getGoalPrice()).member(member).targetMonth(request.getTargetMonth()).build();
        Goal savedGoal = goalRepository.save(goal);
        return ResponseEntity.ok().body(new GoalResponse(savedGoal.getId(), savedGoal.getTargetMonth(), savedGoal.getGoalPrice()));
    }

    @GetMapping("/goal")
    public ResponseEntity<GoalResponse> findByTargetMonth(FindGoalRequest request) {
        Goal goal = goalRepository.findTargetMonthGoal(request.getTargetMonth(), request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목표입니다"));
        return ResponseEntity.ok().body(new GoalResponse(goal.getId(), goal.getTargetMonth(), goal.getGoalPrice()));
    }
}
