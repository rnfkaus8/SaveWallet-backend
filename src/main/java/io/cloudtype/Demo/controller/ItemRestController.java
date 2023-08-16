package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.controller.request.FindItemsRequest;
import io.cloudtype.Demo.controller.request.SaveItemRequest;
import io.cloudtype.Demo.controller.request.UpdateItemRequest;
import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.repository.ItemRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import io.cloudtype.Demo.service.ItemService;
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

    private final ItemService itemService;

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
    public String updateItem(@RequestBody UpdateItemRequest request) {
        itemService.update(request);
        return "success";
    }

    @DeleteMapping("/item/{id}")
    public String delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "success";
    }

}
