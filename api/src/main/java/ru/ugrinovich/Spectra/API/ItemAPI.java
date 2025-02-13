package ru.ugrinovich.Spectra.API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entity.Item;

import java.util.List;
import java.util.UUID;
@RequestMapping("/api/v1/items")
public interface ItemAPI {
    @GetMapping()
    List<Item> getAllItems();

    @PostMapping("/new")
    ResponseEntity<HttpStatus> createItem(@RequestBody Item item);

    @GetMapping("/{itemId}")
    Item getItem(@PathVariable UUID itemId);

    @PatchMapping("/{itemId}/update")
    ResponseEntity<HttpStatus> updateItem(@PathVariable UUID itemId, @RequestBody Item item);

    @DeleteMapping("/{itemId}/delete")
    ResponseEntity<HttpStatus> deleteItem(@PathVariable UUID itemId);

    @GetMapping("/serial/{serialNumber}")
    Item getItem(@PathVariable String serialNumber);
}
