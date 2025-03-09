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
@Schema(description = "Запрос на получение списка покупок покупателя")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ForGetHistoryOfPurchaseRequest {

    @Schema(description = "Уникальный идентификатор покупателя")
    @NotNull
    UUID buyerId;


}
