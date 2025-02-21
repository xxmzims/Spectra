package ru.ugrinovich.Spectra.request.Buyer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ugrinovich.Spectra.request.Item.ItemPurchaseStatus;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Schema(description = "Запрос на получение списка покупок покупателя")
public class ForGetHistoryOfPurchaseRequest {

    @Schema(description = "Уникальный идентификатор покупателя")
    @NotNull
    private UUID buyerId;

    @NotNull
    @Schema(description = "Статус покупки товара")
    private ItemPurchaseStatus itemPurchaseStatus;
}
