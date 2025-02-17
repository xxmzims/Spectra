package ru.ugrinovich.Spectra.response.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ItemResponse {
    private String name;

    private String serialNumber;

    private String description;

    private ItemType category;

}
