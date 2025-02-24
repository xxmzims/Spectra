package ru.ugrinovich.Spectra.response.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ForAdminOfferResponse {

    private UUID offerId;

    private  UUID buyerId;

    private UUID itemId;

    private String buyerName;

    private String itemName;

    private String serialNumber;

    private Instant purchaseDate;

    private int quantity;

    private double totalPrice;
}
