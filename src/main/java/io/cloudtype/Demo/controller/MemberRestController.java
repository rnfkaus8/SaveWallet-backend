package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/member/{name}")
    public Member findByName(@PathVariable("name") String name) {
        return memberRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
    }

    @PostMapping("/member/{name}")
    public Member save(@PathVariable("name") String name) {
        Optional<Member> findMemberOptional = memberRepository.findByName(name);
        if (findMemberOptional.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder().name(name).createdAt(now).build();
        memberRepository.save(member);

        categoryRepository.save(Category.builder().name("식비").member(member).createdAt(now).updatedAt(now).build());
        categoryRepository.save(Category.builder().name("쇼핑").member(member).createdAt(now).updatedAt(now).build());
        categoryRepository.save(Category.builder().name("자기계발").member(member).createdAt(now).updatedAt(now).build());

        return member;
    }
}