package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.FindGoalRequest;
import io.cloudtype.Demo.controller.request.SaveGoalRequest;
import io.cloudtype.Demo.domain.Goal;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.GoalRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoalRestController {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;

    @PutMapping("/goal")
    public Goal saveNotExistGoal(@RequestBody SaveGoalRequest request) {
        Optional<Goal> optionalGoal = goalRepository.findTargetMonthGoal(request.getTargetMonth(), request.getMemberId());
        if (optionalGoal.isPresent()) {
            return optionalGoal.get();
        }

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Goal goal = Goal.builder().goalPrice(request.getGoalPrice()).member(member).targetMonth(request.getTargetMonth()).build();
        return goalRepository.save(goal);
    }

    @GetMapping("/goal")
    public Goal findByTargetMonth(FindGoalRequest request) {
        log.info("targetMonth : {}", request.getTargetMonth());
        log.info("memberId: {}", request.getTargetMonth());
        return goalRepository.findTargetMonthGoal(request.getTargetMonth(), request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목표입니다"));
    }
}
