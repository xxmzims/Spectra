package ru.ugrinovich.Spectra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.ugrinovich.Spectra.dto.ItemDTO;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
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
