package ru.ugrinovich.Spectra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.response.Item.ForAdminOfferResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PurchaseHistoryMapper {

    PurchaseHistoryMapper INSTANCE = Mappers.getMapper(PurchaseHistoryMapper.class);

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.serialNumber", target = "serialNumber")
    @Mapping(source = "item.name", target = "name")
    ItemPurchaseHistoryResponse toItemPurchaseHistoryResponse(ItemPurchase purchase);

    @Mapping(source = "id", target = "offerId")
    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "buyer.firstName", target = "buyerName")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.serialNumber", target = "serialNumber")
    ForAdminOfferResponse toForAdminOfferResponse(ItemPurchase purchase);

    default List<ItemPurchaseHistoryResponse> toResponseList(List<ItemPurchase> purchases) {
        return purchases.stream()
                .map(this::toItemPurchaseHistoryResponse)
                .collect(Collectors.toList());
    }

    default List<ForAdminOfferResponse> toAdminOfferResponseList(List<ItemPurchase> purchases) {
        return purchases.stream()
                .map(this::toForAdminOfferResponse)
                .collect(Collectors.toList());
    }
}