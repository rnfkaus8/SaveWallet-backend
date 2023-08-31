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

        Member member = Member.builder().name(name).build();
        memberRepository.save(member);

        categoryRepository.save(Category.builder().member(member).name("식비").build());
        categoryRepository.save(Category.builder().member(member).name("쇼핑").build());
        categoryRepository.save(Category.builder().member(member).name("문화").build());
        categoryRepository.save(Category.builder().member(member).name("취미/여가").build());
        categoryRepository.save(Category.builder().member(member).name("교통").build());
        categoryRepository.save(Category.builder().member(member).name("그 외").build());

        return member;
    }
}
