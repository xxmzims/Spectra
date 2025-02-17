package ru.ugrinovich.Spectra.controllers;

import lombok.extern.slf4j.Slf4j;
import ru.ugrinovich.Spectra.API.ItemAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.dto.ItemDTO;
import ru.ugrinovich.Spectra.entity.Item;
import ru.ugrinovich.Spectra.mapper.ItemMapper;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.item.ItemService;
import ru.ugrinovich.Spectra.services.item.ItemServiceLocalImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController implements ItemAPI {

    private final ItemMapper itemMapper;
    private final ItemService itemsService;

    public List<ItemResponse> getAllItems() {
        List<Item> items = itemsService.getAllItems();
        log.info("Найдены товары с id {}", items.stream().map(Item::getId).collect(Collectors.toList()));
        return itemMapper.toItemResponseList(items);
    }

    public ResponseEntity<HttpStatus> createItem(ItemCreateRequest itemCreateRequest) {
        ItemDTO itemDTO = itemMapper.toItemDTO(itemCreateRequest);
        Item item = itemMapper.toItem(itemDTO);
        itemsService.save(item);
        log.info("Создан товар с id {}", item.getId());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public ItemResponse getItem(UUID id) {
        Item item = itemsService.getItemById(id);
        log.info("Найден товар с id {}", id);
        return itemMapper.toItemResponse(item);
    }

    public ResponseEntity<HttpStatus> updateItem(UUID id, ItemUpdateRequest itemUpdateRequest) {
        ItemDTO itemDTO = itemMapper.toItemDTO(itemUpdateRequest);
        Item item = itemMapper.toItem(itemDTO);
        itemsService.updateById(id, item);
        log.info("Обновлены данные товара с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> deleteItem(UUID id) {
        itemsService.deleteById(id);
        log.info("Удален товар с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ItemResponse getItem(String serialNumber) {
        Item item = itemsService.findBySerialNumber(serialNumber);

        log.info("Найден товар с серийным номером {}" , serialNumber);
        return itemMapper.toItemResponse(item);
    }
}
