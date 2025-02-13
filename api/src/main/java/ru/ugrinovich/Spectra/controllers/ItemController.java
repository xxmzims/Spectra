package ru.ugrinovich.Spectra.controllers;

import ru.ugrinovich.Spectra.API.ItemAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entity.Item;
import ru.ugrinovich.Spectra.services.item.ItemServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemAPI {

    public final ItemServiceImpl itemsService;

    public List<Item> getAllItems() {
        return itemsService.getAllItems();
    }

    public ResponseEntity<HttpStatus> createItem(Item item) {
        itemsService.save(item);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public Item getItem(UUID id) {
        return itemsService.getItemById(id);
    }

    public ResponseEntity<HttpStatus> updateItem(UUID id, Item item) {
        itemsService.updateById(id, item);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> deleteItem(UUID id) {
        itemsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public Item getItem(String serialNumber) {
        return itemsService.findBySerialNumber(serialNumber);
    }
}
