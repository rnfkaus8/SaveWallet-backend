package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.FindItemsRequest;
import io.cloudtype.Demo.controller.request.SaveItemRequest;
import io.cloudtype.Demo.controller.request.UpdateItemRequest;
import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.repository.ItemRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemRestController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/items")
    public List<Item> getItems(FindItemsRequest request) {
        return itemRepository.findItems(request.getStartDate(), request.getEndDate(), request.getMemberId());
    }

    @PostMapping("/item")
    public Item saveItem(@RequestBody SaveItemRequest request) {
        return itemRepository.save(Item.builder()
                .member(memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("잘못된 아이디입니다")))
                .name(request.getName())
                .price(request.getPrice())
                .boughtDate(request.getBoughtDate())
                .build());
    }

    @PatchMapping("/item")
    public Item updateItem(@RequestBody UpdateItemRequest request) {
        Item findItem = itemRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
        findItem.updateItem(request.getName(), request.getPrice(), request.getBoughtDate());
        return findItem;
    }

    @DeleteMapping("/item/{id}")
    public String delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "success";
    }

}
