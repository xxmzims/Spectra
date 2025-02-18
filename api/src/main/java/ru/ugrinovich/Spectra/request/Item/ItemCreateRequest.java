package ru.ugrinovich.Spectra.request.Item;

import io.swagger.v3.oas.annotations.media.Schema;
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
}
