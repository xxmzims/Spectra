package ru.ugrinovich.Spectra.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.ItemType;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private String name;

    private String serialNumber;

    private String description;

    private ItemType category;

    private Buyer buyer;

    private double price;

    private int amount;

}
