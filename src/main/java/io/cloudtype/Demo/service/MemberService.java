package io.cloudtype.Demo.service;

import io.cloudtype.Demo.controller.response.MemberResponse;
import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public MemberResponse saveNotExistMember(String deviceId) {
        Optional<Member> findByDeviceId = memberRepository.findByDeviceId(deviceId);
        if (findByDeviceId.isPresent()) {
            Member existMember = findByDeviceId.get();
            return new MemberResponse(existMember.getId(), existMember.getDeviceId());
        }

        Member member = Member.builder().deviceId(deviceId).build();
        memberRepository.save(member);

        categoryRepository.save(Category.builder().member(member).name("식비").build());
        categoryRepository.save(Category.builder().member(member).name("쇼핑").build());
        categoryRepository.save(Category.builder().member(member).name("문화").build());
        categoryRepository.save(Category.builder().member(member).name("취미/여가").build());
        categoryRepository.save(Category.builder().member(member).name("교통").build());
        categoryRepository.save(Category.builder().member(member).name("그 외").build());
        return new MemberResponse(member.getId(), member.getDeviceId());
    }

}
