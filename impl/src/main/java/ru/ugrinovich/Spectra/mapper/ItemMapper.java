package ru.ugrinovich.Spectra.mapper;

import org.mapstruct.Mapper;
import ru.ugrinovich.Spectra.dto.BuyerDTO;
import ru.ugrinovich.Spectra.dto.ItemDTO;
import ru.ugrinovich.Spectra.entity.Buyer;
import ru.ugrinovich.Spectra.entity.Item;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemCreateRequest itemCreateRequest);

    Item toItem(ItemUpdateRequest itemUpdateRequest);

    Item toItemDTO(Item item);

    Item toItem(ItemDTO itemDTO);

    ItemResponse toItemResponse(Item item);

    ItemDTO toItemDTO(ItemResponse itemResponse);

    List<ItemResponse> toItemResponseList(List<Item> items);

    ItemDTO toItemDTO (ItemCreateRequest itemCreateRequest);

    ItemDTO toItemDTO (ItemUpdateRequest itemUpdateRequest);
}
