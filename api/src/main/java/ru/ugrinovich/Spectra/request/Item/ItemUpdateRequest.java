package ru.ugrinovich.Spectra.request.Item;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String name;

    @Schema(description = "Серийный номер товара", requiredMode = REQUIRED)
    private String serialNumber;

    @Schema(description = "Описание товара", requiredMode = REQUIRED)
    private String description;

    @Schema(description = "Категория товара", requiredMode = REQUIRED, example = "PHONE")
    private ItemType category;
}
