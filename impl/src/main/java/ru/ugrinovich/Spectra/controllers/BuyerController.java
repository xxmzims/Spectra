package ru.ugrinovich.Spectra.controllers;

import lombok.extern.slf4j.Slf4j;
import ru.ugrinovich.Spectra.API.BuyerAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.dto.BuyerDTO;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.mapper.BuyerMapper;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.services.buyer.BuyerService;
import ru.ugrinovich.Spectra.services.buyer.BuyerServiceLocalImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BuyerController implements BuyerAPI {

    private final BuyerService buyersService;

    private final BuyerMapper buyerMapper;

    public List<BuyerResponse> findAllBuyers() {
        List<Buyer> buyers = buyersService.findAllBuyers();
        List<BuyerResponse> buyerResponseList =  buyerMapper.toBuyerResponses(buyers);
        log.info("Найдены покупатели с id {}", buyers.stream().map(Buyer::getId).collect(Collectors.toList()));

        return buyerResponseList;
    }

    public ResponseEntity<HttpStatus> createBuyer(BuyerCreateRequest buyerCreateRequest) {
        BuyerDTO buyerDTO = buyerMapper.toBuyerDTO(buyerCreateRequest);
        Buyer buyer = buyerMapper.toBuyer(buyerDTO);
        buyersService.save(buyer);
        log.info("Создан покупатель с id {}", buyer.getId());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public BuyerResponse getBuyer(UUID id) {
        Buyer buyer = buyersService.findById(id);
        BuyerDTO buyerDTO =  buyerMapper.toBuyerDTO(buyer);
        log.info("Найден покупатель с id {}", buyer.getId());

        return buyerMapper.toBuyerResponse(buyerDTO);
    }

    public ResponseEntity<HttpStatus> deleteBuyer(UUID id) {
        buyersService.deleteById(id);
        log.info("Удален покупатель с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> updateBuyer(UUID id, BuyerUpdateRequest buyerUpdateRequest) {
        BuyerDTO buyerDTO = buyerMapper.toBuyerDTO(buyerUpdateRequest);
        Buyer buyer = buyerMapper.toBuyer(buyerDTO);
        buyersService.updateById(id, buyer);
        log.info("Обновлены данные покупателя с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    public ResponseEntity<HttpStatus> assignItem(UUID id, UUID item_id) {
        buyersService.assignItemToBuyer(id, item_id);
        log.info("Для покупателя с id {} назначен товар с id {}", id, item_id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
