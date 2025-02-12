package ru.ugrinovich.Spectra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.models.Buyer;
import ru.ugrinovich.Spectra.services.BuyersService;

import java.util.List;

@RestController
@RequestMapping("/buyers")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyersService buyersService;

    @GetMapping()
    private List<Buyer> findAllBuyers(){
        return buyersService.findAllBuyers();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createBuyer(@RequestBody Buyer buyer){
        buyersService.save(buyer);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Buyer getBuyerById(@PathVariable int id){
        return buyersService.findById(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id){
        buyersService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateById(@PathVariable int id, @RequestBody Buyer buyer){
        buyersService.updateById(id, buyer);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}/assign_item/{item_id}")
    public ResponseEntity<HttpStatus> assignItem(@PathVariable int id, @PathVariable int item_id){
        buyersService.assignItemToBuyer(id, item_id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
