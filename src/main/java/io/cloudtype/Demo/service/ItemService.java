package io.cloudtype.Demo.service;

import io.cloudtype.Demo.controller.request.SaveItemRequest;
import io.cloudtype.Demo.controller.request.UpdateItemRequest;
import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.ItemRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void update(UpdateItemRequest request) {
        Item findItem = itemRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
        findItem.updateItem(request.getName(), request.getPrice(), request.getBoughtDate());
    }

    @Transactional
    public void save(SaveItemRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("잘못된 아이디입니다"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        itemRepository.save(Item.builder()
                .member(member)
                .category(category)
                .name(request.getName())
                .price(request.getPrice())
                .boughtDate(request.getBoughtDate())
                .build());
    }
}
