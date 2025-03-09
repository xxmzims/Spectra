package ru.ugrinovich.Spectra.request.Buyer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Schema(description = "Запрос на добавление в список покупок товара")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ForAddItemToPurchaseListRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор покупателя")
    UUID buyerId;

    @NotNull
    @Schema(description = "Уникальный идентификатор товара")
    UUID itemId;

    @NotNull
    @Schema(description = "Цена единицы товара")
    Double price;

    @NotNull
    @Schema(description = "Количество добавляемого товара")
    int quantity;
}
