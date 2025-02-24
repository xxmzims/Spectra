package ru.ugrinovich.Spectra.mappers;

import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.response.Item.ForAdminOfferResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseHistoryMapper {

    public List<ItemPurchaseHistoryResponse> toResponse(List<ItemPurchase> purchases) {
        return purchases.stream().map(purchase -> ItemPurchaseHistoryResponse.builder()
                .itemId(purchase.getItem().getId())
                .purchaseDate(purchase.getPurchaseDate())
                .serialNumber(purchase.getItem().getSerialNumber())
                .name(purchase.getItem().getName())
                .quantity(purchase.getQuantity())
                .totalPrice(purchase.getTotalPrice())
                .build()).collect(Collectors.toList());
    }
    public List<ForAdminOfferResponse> toAdminOfferResponse(List<ItemPurchase> purchases){
        return purchases.stream().map(purchase -> ForAdminOfferResponse.builder()
                .offerId(purchase.getId())
                .buyerId(purchase.getBuyer().getId())
                .itemId(purchase.getItem().getId())
                .buyerName(purchase.getBuyer().getFirstName())
                .itemName(purchase.getItem().getName())
                .serialNumber(purchase.getItem().getSerialNumber())
                .purchaseDate(purchase.getPurchaseDate())
                .quantity(purchase.getQuantity())
                .totalPrice(purchase.getTotalPrice()).build()).collect(Collectors.toList());
    }
}
