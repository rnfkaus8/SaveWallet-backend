package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.domain.Member;
import io.cloudtype.Demo.repository.ItemRepository;
import io.cloudtype.Demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemRestController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/items")
    public List<Item> getItems(@RequestBody FindItemsRequest request) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        log.info("startDate : {}", request.getStartDate());
        log.info("endDate : {}", request.getEndDate());
        LocalDateTime startDate = LocalDate.parse(request.getStartDate(), dateTimeFormatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(request.getEndDate(), dateTimeFormatter).atStartOfDay();
        log.info("startDate parse : {}", startDate);
        log.info("endDate parse : {}", endDate);
        return itemRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @PostMapping("/item")
    public Item saveItem(@RequestBody SaveItemRequest request) {
        return itemRepository.save(Item.builder()
                .createdAt(LocalDateTime.now())
                .member(memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("잘못된 아이디입니다")))
                .name(request.getName())
                .price(request.getPrice())
                .build());
    }

}
