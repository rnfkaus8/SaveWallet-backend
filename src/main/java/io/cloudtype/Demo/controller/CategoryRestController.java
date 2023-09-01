package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.SaveCategoryRequest;
import io.cloudtype.Demo.controller.request.UpdateCategoryRequest;
import io.cloudtype.Demo.controller.response.CategoryResponse;
import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/category")
    public Category save(@RequestBody SaveCategoryRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return categoryRepository.save(Category.builder().name(request.getName()).member(member).build());
    }

    @GetMapping("/categories/{id}")
    public List<Category> findAllByMemberId(@PathVariable Long id) {
        return categoryRepository.findByMemberId(id).stream().map((category -> {return new CategoryResponse(category.getId(), category.getName())
        }));
    }

    @GetMapping("/category/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }

    @PatchMapping("/category")
    public Category update(@RequestBody UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        category.changeName(request.getName());
        return category;
    }

    @DeleteMapping("/category/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "success";
    }
}
