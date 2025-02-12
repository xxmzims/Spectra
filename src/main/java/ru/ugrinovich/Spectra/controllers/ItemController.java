package ru.ugrinovich.Spectra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.models.Item;
import ru.ugrinovich.Spectra.services.ItemsService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    public final ItemsService itemsService;

    @GetMapping()
    public List<Item> getAllItems(){
        return  itemsService.getAllItems();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createItem(@RequestBody Item item){
        itemsService.save(item);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable int id){
        return itemsService.getItemById(id);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateItemById(@PathVariable int id, @RequestBody Item item){
        itemsService.updateById(id, item);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable int id){
        itemsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping("/serial/{serialNumber}")
    public Item findBySerialNumber(@PathVariable String serialNumber){
        return itemsService.findBySerialNumber(serialNumber);
    }
}
