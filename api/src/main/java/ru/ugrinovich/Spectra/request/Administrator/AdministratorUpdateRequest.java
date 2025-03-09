package ru.ugrinovich.Spectra.request.Administrator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Schema(description = "Данные администратора для обновления")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AdministratorUpdateRequest {

    @Schema(description = "Имя администратора", requiredMode = REQUIRED)
    @NotBlank
    String name;
}
