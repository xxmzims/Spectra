package ru.ugrinovich.Spectra.response.Item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ugrinovich.Spectra.request.Item.ItemType;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponse {

    UUID id;

    String name;

    String serialNumber;

    String description;

    ItemType category;

    double price;

    int amount;

}
