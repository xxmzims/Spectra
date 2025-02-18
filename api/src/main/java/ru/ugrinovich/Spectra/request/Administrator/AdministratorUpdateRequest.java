package ru.ugrinovich.Spectra.request.Administrator;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Данные администратора для обновления")
public class AdministratorUpdateRequest {

    @Schema(description = "Имя администратора", requiredMode = REQUIRED)
    @NotNull
    private String name;
}
