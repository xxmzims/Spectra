package ru.ugrinovich.Spectra.API;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entity.Buyer;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/buyers")
public interface BuyerAPI {
    @GetMapping()
    List<Buyer> findAllBuyers();

    @PostMapping("/new")
    ResponseEntity<HttpStatus> createBuyer(@RequestBody Buyer buyer);

    @GetMapping("/{buyerId}")
    Buyer getBuyer(@PathVariable UUID buyerId);

    @DeleteMapping("/{buyerId}/delete")
    ResponseEntity<HttpStatus> deleteBuyer(@PathVariable UUID buyerId);


    @PatchMapping("/{buyerId}/update")
    ResponseEntity<HttpStatus> updateBuyer(@PathVariable UUID buyerId, @RequestBody Buyer buyer);


    @PatchMapping("/{buyerId}/assign_item/{itemId}")
    ResponseEntity<HttpStatus> assignItem(@PathVariable UUID buyerId, @PathVariable UUID itemId);

}
