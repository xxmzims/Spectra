package ru.ugrinovich.Spectra.request.Buyer;

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
@Schema(description = "Данные покупателя для обновления")
public class BuyerUpdateRequest {

    @Schema(description = "Имя", requiredMode = REQUIRED)
    private String firstName;

    @Schema(description = "Фамилия", requiredMode = REQUIRED)
    private String secondName;

    @Schema(description = "Возраст", requiredMode = REQUIRED, example = "18")
    private int age;

    @Schema(description = "Email-адрес", requiredMode = REQUIRED, example = "xxmzims@gmail.com")
    private String email;
}
