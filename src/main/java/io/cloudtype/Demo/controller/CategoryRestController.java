package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/category")
    public Category save(@RequestBody SaveCategoryRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        LocalDateTime now = LocalDateTime.now();
        return categoryRepository.save(Category.builder().name(request.getName()).member(member).createdAt(now).updatedAt(now).build());
    }

    @GetMapping("/categories/{id}")
    public List<Category> findAllById(@PathVariable Long id) {
        return categoryRepository.findByMemberId(id);
    }

    @GetMapping("/category/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }

    @PatchMapping("/category")
    public Category update(@RequestBody UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        category.changeName(request.getName());
        return null;
    }

    @DeleteMapping("/category/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "success";
    }

}
