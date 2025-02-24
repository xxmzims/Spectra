package ru.ugrinovich.Spectra.response.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ItemRemainingResponse {

    private UUID id;

    private String name;

    private String serialNumber;

    private int amount;

    private int price;

    private int totalPrice;

}
