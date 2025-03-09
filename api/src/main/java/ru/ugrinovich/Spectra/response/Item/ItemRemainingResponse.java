package ru.ugrinovich.Spectra.response.Item;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ItemRemainingResponse {

    UUID id;

    String name;

    String serialNumber;

    int amount;

    int price;

    int totalPrice;

}
