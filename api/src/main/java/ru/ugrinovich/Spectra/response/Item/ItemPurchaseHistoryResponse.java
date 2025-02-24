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
public class ItemPurchaseHistoryResponse {

        private UUID itemId;

        private String name;

        private String serialNumber;

        private Instant purchaseDate;

        private int quantity;

        private double totalPrice;

}
