package ru.ugrinovich.Spectra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ugrinovich.Spectra.dto.BuyerDTO;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerResponse;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerWithItemsResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    Buyer toBuyer(BuyerDTO buyerDTO);

    Buyer toBuyer(BuyerCreateRequest buyerCreateRequest);

    Buyer toBuyer(BuyerUpdateRequest buyerUpdateRequest);

    BuyerResponse toBuyerResponse(Buyer buyer);

    BuyerDTO toBuyerDTO(Buyer buyer);

    BuyerResponse toBuyerResponse(BuyerDTO buyerDTO);

    BuyerDTO toBuyerDTO(BuyerUpdateRequest buyerUpdateRequest);

    BuyerDTO toBuyerDTO(BuyerCreateRequest buyerCreateRequest);

    List<BuyerResponse> toBuyerResponses(List<Buyer> buyers);

    ForAdministratorBuyerResponse toForAdministratorBuyerResponse(Buyer buyer);

    @Mapping(target = "purchases", source = "purchases")
    List<ForAdministratorBuyerWithItemsResponse> toForAdministratorBuyerWithItemsResponse(List<Buyer> buyers);

    default List<ItemPurchaseHistoryResponse> map(List<ItemPurchase> purchases) {
        if (purchases == null) {
            return Collections.emptyList();
        }
        return PurchaseHistoryMapper.INSTANCE.toResponseList(purchases);
    }
}

