package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.response.MemberResponse;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.MemberRepository;
import io.cloudtype.Demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/member/{deviceId}")
    public MemberResponse findByName(@PathVariable("deviceId") String deviceId) {
        Member findMember = memberRepository.findByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
        return new MemberResponse(findMember.getId(), findMember.getDeviceId());
    }

    @PutMapping("/member/{deviceId}")
    public MemberResponse saveNotExist(@PathVariable("deviceId") String deviceId) {
        return memberService.saveNotExistMember(deviceId);
    }
}
