package ru.ugrinovich.Spectra.response.Byer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ForAdministratorBuyerWithItemsResponse {

    private UUID id;

    private String firstName;

    private String secondName;

    private int age;

    private String email;

    private Instant createAt;

    private Instant updateAt;

    private List<ItemPurchaseHistoryResponse> purchases;
}
