package io.cloudtype.Demo;

import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitMemberDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initDb();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepository memberRepository;

        public void initDb() {
            memberRepository.save(Member.builder().id(1L).name("박채윤").build());
            memberRepository.save(Member.builder().id(2L).name("박이영").build());
        }
    }
}
