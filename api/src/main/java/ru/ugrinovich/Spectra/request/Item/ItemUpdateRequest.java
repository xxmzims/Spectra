package ru.ugrinovich.Spectra.request.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Schema(description = "Данные для обновления товара")
public class ItemUpdateRequest {

    @Schema(description = "Наименование товара", requiredMode = REQUIRED)
    String name;

    @Schema(description = "Серийный номер товара", requiredMode = REQUIRED)
    String serialNumber;

    @Schema(description = "Описание товара", requiredMode = REQUIRED)
    String description;

    @Schema(description = "Категория товара", requiredMode = REQUIRED, example = "PHONE")
    ItemType category;

    @Schema(description = "Цена товара", requiredMode = REQUIRED, example = "25.3")
    @DecimalMin(value = "0.01", message = "Цена товара не может быть меньше 0.01")
    @NotNull
    double price;

    @Schema(description = "Количество товара", requiredMode = REQUIRED)
    @Min(value = 0, message = "Количество товара не может быть меньше 0")
    int amount;

}
