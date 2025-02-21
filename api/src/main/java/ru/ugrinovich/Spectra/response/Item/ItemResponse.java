package ru.ugrinovich.Spectra.response.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ugrinovich.Spectra.request.Item.ItemType;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ItemResponse {
    private String name;

    private String serialNumber;

    private String description;

    private ItemType category;

    private double price;

    private int amount;

}
