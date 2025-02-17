package ru.ugrinovich.Spectra.request.Administrator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Schema(description = "Данные администратора для создания")
public class AdministratorCreateRequest {

    @Schema(description = "Имя администратора", requiredMode = REQUIRED)
    private String name;
}
