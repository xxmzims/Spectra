package ru.ugrinovich.Spectra.API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;
@RequestMapping("/api/v1/items")
public interface ItemAPI {
    @GetMapping()
    List<ItemResponse> getAllItems();

    @PostMapping("/new")
    ResponseEntity<HttpStatus> createItem(@RequestBody ItemCreateRequest item);

    @GetMapping("/{itemId}")
    ItemResponse getItem(@PathVariable UUID itemId);

    @PatchMapping("/{itemId}/update")
    ResponseEntity<HttpStatus> updateItem(@PathVariable UUID itemId, @RequestBody ItemUpdateRequest item);

    @DeleteMapping("/{itemId}/delete")
    ResponseEntity<HttpStatus> deleteItem(@PathVariable UUID itemId);

    @GetMapping("/serial/{serialNumber}")
    ItemResponse getItem(@PathVariable String serialNumber);
}
