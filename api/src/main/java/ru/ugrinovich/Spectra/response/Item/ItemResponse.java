package ru.ugrinovich.Spectra.response.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

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
