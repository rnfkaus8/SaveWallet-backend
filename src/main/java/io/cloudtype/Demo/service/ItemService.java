package io.cloudtype.Demo.service;

import io.cloudtype.Demo.controller.request.FindItemsRequest;
import io.cloudtype.Demo.controller.request.SaveItemRequest;
import io.cloudtype.Demo.controller.request.UpdateItemRequest;
import io.cloudtype.Demo.controller.response.ItemResponse;
import io.cloudtype.Demo.controller.response.TotalPriceByCategoryResponse;
import io.cloudtype.Demo.domain.Category;
import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.CategoryRepository;
import io.cloudtype.Demo.repository.ItemRepository;
import io.cloudtype.Demo.repository.ItemRepositorySupport;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.cloudtype.Demo.domain.QCategory.category;
import static io.cloudtype.Demo.domain.QItem.item;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepositorySupport itemRepositorySupport;

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

    public List<ItemResponse> findItems(FindItemsRequest request) {
        return itemRepository.findItems(request.getStartDate(), request.getEndDate(), request.getMemberId()).stream().map((item) -> {
            return new ItemResponse(item.getId(), item.getName(), item.getPrice(), item.getBoughtDate());}).collect(Collectors.toList());
    }

    public List<TotalPriceByCategoryResponse> findTotalPriceByCategory(FindItemsRequest request) {
        return itemRepositorySupport.getCategoryTotalCount(request).stream().map(tuple ->
                new TotalPriceByCategoryResponse(tuple.get(category.name), tuple.get(item.price.sum()))).collect(Collectors.toList());
    }
}
