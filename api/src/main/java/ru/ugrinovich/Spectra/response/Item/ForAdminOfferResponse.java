package ru.ugrinovich.Spectra.response.Item;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForAdminOfferResponse {

    UUID offerId;

    UUID buyerId;

    UUID itemId;

    String buyerName;

    String itemName;

    String serialNumber;

    Instant purchaseDate;

    int quantity;

    double totalPrice;
}
