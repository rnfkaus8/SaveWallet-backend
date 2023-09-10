package io.cloudtype.Demo;

import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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
        private final CategoryRepository categoryRepository;

        public void initDb() {
            Member 박채윤 = Member.builder().id(1L).name("박채윤").build();
            Member 박이영 = Member.builder().id(2L).name("박이영").build();
            memberRepository.save(박채윤);
            memberRepository.save(박이영);
            categoryRepository.save(Category.builder().member(박이영).name("식비").color("#FF5757").build());
            categoryRepository.save(Category.builder().member(박이영).name("쇼핑").color("#467AFF").build());
            categoryRepository.save(Category.builder().member(박이영).name("취미/여가").color("#FFD542").build());
            categoryRepository.save(Category.builder().member(박이영).name("교통").color("#42D93F").build());
            categoryRepository.save(Category.builder().member(박이영).name("그 외").color("#8E8565").build());

            categoryRepository.save(Category.builder().member(박채윤).name("식비").color("#FF5757").build());
            categoryRepository.save(Category.builder().member(박채윤).name("쇼핑").color("#467AFF").build());
            categoryRepository.save(Category.builder().member(박채윤).name("취미/여가").color("#FFD542").build());
            categoryRepository.save(Category.builder().member(박채윤).name("교통").color("#42D93F").build());
            categoryRepository.save(Category.builder().member(박채윤).name("그 외").color("#8E8565").build());
        }
    }
}
