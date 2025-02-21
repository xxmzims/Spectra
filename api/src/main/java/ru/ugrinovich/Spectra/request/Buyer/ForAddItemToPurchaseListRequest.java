package ru.ugrinovich.Spectra.request.Buyer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ugrinovich.Spectra.request.Item.ItemPurchaseStatus;
import ru.ugrinovich.Spectra.validation.annotations.EnumValidate;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Schema(description = "Запрос на добавление в список покупок товара")
public class ForAddItemToPurchaseListRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор покупателя")
    private UUID buyerId;

    @NotNull
    @Schema(description = "Уникальный идентификатор товара")
    private UUID itemId;

    @NotNull
    @Schema(description = "Тип запроса(Заказан, В корзине, Куплен)")
    @EnumValidate(enumClass = ItemPurchaseStatus.class, message = "Неверный тип запроса")
    private ItemPurchaseStatus itemPurchaseStatus;
}
