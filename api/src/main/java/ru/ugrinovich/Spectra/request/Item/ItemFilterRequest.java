package ru.ugrinovich.Spectra.request.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import ru.ugrinovich.Spectra.validation.annotations.EnumValidate;

@Data
@Schema(description = "Объект для передачи данных о фильтрации")
public class ItemFilterRequest {

    @Schema(description = "Категория товара", example = "PHONE")
    @EnumValidate(enumClass = ItemType.class, message = "Неверная категория")
    private ItemType category;

    @Schema(description = "Минимальное количество товара", example = "0")
    @Min(0)
    private Integer amount;

    @Schema(description = "Минимальная цена товара", example = "0.1")
    @Min(0)
    private Double startPrice;

    @Schema(description = "Максимальная цена товара", example = "10.5")
    @Min(0)
    private Double endPrice;

    @Schema(description = "Номер страницы", example = "0")
    @Min(0)
    private Integer offset = 0;

    @Schema(description = "Количество элементов на странице", example = "10")
    @Min(0)
    private Integer limit = 5;

    @Schema(description = "Сортировка товара", example = "DATE_ASC")
    private ItemTypeSort order = ItemTypeSort.DATE_ASC;

}
