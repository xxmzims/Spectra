package ru.ugrinovich.Spectra.response.Byer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForAdministratorBuyerWithItemsResponse {

    UUID id;

    String firstName;

    String secondName;

    int age;

    String email;

    Instant createAt;

    Instant updateAt;

    List<ItemPurchaseHistoryResponse> purchases;
}
