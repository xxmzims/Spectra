package ru.ugrinovich.Spectra.mapper;

import org.mapstruct.Mapper;
import ru.ugrinovich.Spectra.dto.BuyerDTO;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;

import java.util.List;

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
}
