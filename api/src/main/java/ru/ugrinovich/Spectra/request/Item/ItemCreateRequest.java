package ru.ugrinovich.Spectra.request.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static ru.ugrinovich.Spectra.validation.util.MessageValidationUtil.ERROR_MESSAGE_FOR_MIN_MAX_SERIAL_NUMBER;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Schema(description = "Данные для создания товара")
public class ItemCreateRequest {

    @Schema(description = "Наименование товара", requiredMode = REQUIRED)
    @NotNull
    private String name;

    @Schema(description = "Серийный номер товара", requiredMode = REQUIRED)
    @NotNull
    @Size(min = 5, max = 60, message = ERROR_MESSAGE_FOR_MIN_MAX_SERIAL_NUMBER)
    private String serialNumber;

    @Schema(description = "Описание товара", requiredMode = REQUIRED)
    @NotNull
    private String description;

    @Schema(description = "Категория товара", requiredMode = REQUIRED, example = "PHONE")
    @NotNull
    private ItemType category;

    @Schema(description = "Цена товара", requiredMode = REQUIRED, example = "25.3")
    @DecimalMin(value = "0.01", message = "Цена товара не может быть меньше 0.01")
    @NotNull
    private double price;

    @Schema(description = "Количество товара", requiredMode = REQUIRED)
    @Min(value = 0, message = "Количество товара не может быть меньше 0")
    private int amount;

}
