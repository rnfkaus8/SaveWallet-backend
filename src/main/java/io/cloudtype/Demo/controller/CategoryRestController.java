package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.SaveCategoryRequest;
import io.cloudtype.Demo.controller.request.UpdateCategoryRequest;
import io.cloudtype.Demo.controller.response.CategoryResponse;
import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> save(@RequestBody SaveCategoryRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Category savedCategory = categoryRepository.save(Category.builder().name(request.getName()).color(request.getColor()).member(member).build());
        return ResponseEntity.ok().body(new CategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getColor()));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<CategoryResponse>> findAllByMemberId(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryRepository.findByMemberId(id).stream().map((category -> {
            return new CategoryResponse(category.getId(), category.getName(), category.getColor());
        })).collect(Collectors.toList()));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        Category savedCategory = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        return ResponseEntity.ok().body(new CategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getColor()));
    }

    @PatchMapping("/category")
    public ResponseEntity<CategoryResponse> update(@RequestBody UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        category.changeName(request.getName());
        return ResponseEntity.ok().body(new CategoryResponse(category.getId(), category.getName(), category.getColor()));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().body("success");
    }
}
