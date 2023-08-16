package io.cloudtype.Demo.service;

import io.cloudtype.Demo.controller.request.UpdateItemRequest;
import io.cloudtype.Demo.domain.Item;
import io.cloudtype.Demo.repository.ItemRepository;
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

    @Transactional
    public void update(UpdateItemRequest request) {
        Item findItem = itemRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));
        findItem.updateItem(request.getName(), request.getPrice(), request.getBoughtDate());
    }
}
