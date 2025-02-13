package ru.ugrinovich.Spectra.controllers;

import ru.ugrinovich.Spectra.API.BuyerAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.services.buyer.BuyerServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BuyerController implements BuyerAPI {

    private final BuyerServiceImpl buyersService;

    public List<Buyer> findAllBuyers() {
        return buyersService.findAllBuyers();
    }

    public ResponseEntity<HttpStatus> createBuyer(Buyer buyer) {
        buyersService.save(buyer);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public Buyer getBuyer(UUID id) {
        return buyersService.findById(id);
    }

    public ResponseEntity<HttpStatus> deleteBuyer(UUID id) {
        buyersService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> updateBuyer(UUID id, Buyer buyer) {
        buyersService.updateById(id, buyer);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    public ResponseEntity<HttpStatus> assignItem(UUID id, UUID item_id) {
        buyersService.assignItemToBuyer(id, item_id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
