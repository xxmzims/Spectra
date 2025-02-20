package ru.ugrinovich.Spectra.request.Buyer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static ru.ugrinovich.Spectra.validation.util.MessageValidationUtil.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder

@Schema(description = "Данные покупателя для создания")
public class BuyerCreateRequest {

    @Schema(description = "Имя", requiredMode = REQUIRED)
    @NotBlank
    @Size(min = 2, max = 100, message = ERROR_MESSAGE_FOR_MIN_MAX_SIZE_FIRST_NAME_SECOND_NAME)
    private String firstName;

    @Schema(description = "Фамилия", requiredMode = REQUIRED)
    @NotBlank
    @Size(min = 2, max = 100, message = ERROR_MESSAGE_FOR_MIN_MAX_SIZE_FIRST_NAME_SECOND_NAME)
    private String secondName;

    @Schema(description = "Возраст", requiredMode = REQUIRED, example = "18")
    @NotBlank
    @Min(value = 0, message = ERROR_MESSAGE_FOR_MIN_MAX_AGE)
    @Max(value = 200, message = ERROR_MESSAGE_FOR_MIN_MAX_AGE)
    private int age;

    @Schema(description = "Email-адрес", requiredMode = REQUIRED, example = "xxmzims@gmail.com")
    @NotBlank
    @Email(message = ERROR_MESSAGE_FOR_EMAIL)
    private String email;
}
