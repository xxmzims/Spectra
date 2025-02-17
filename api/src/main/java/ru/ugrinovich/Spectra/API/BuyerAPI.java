package ru.ugrinovich.Spectra.API;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/buyers")
public interface BuyerAPI {
    @GetMapping()
    List<BuyerResponse> findAllBuyers();

    @PostMapping("/new")
    ResponseEntity<HttpStatus> createBuyer(@RequestBody BuyerCreateRequest buyer);

    @GetMapping("/{buyerId}")
    BuyerResponse getBuyer(@PathVariable UUID buyerId);

    @DeleteMapping("/{buyerId}/delete")
    ResponseEntity<HttpStatus> deleteBuyer(@PathVariable UUID buyerId);


    @PatchMapping("/{buyerId}/update")
    ResponseEntity<HttpStatus> updateBuyer(@PathVariable UUID buyerId, @RequestBody BuyerUpdateRequest buyer);


    @PatchMapping("/{buyerId}/assign_item/{itemId}")
    ResponseEntity<HttpStatus> assignItem(@PathVariable UUID buyerId, @PathVariable UUID itemId);

}
