package ru.ugrinovich.Spectra.API;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;

import java.util.List;
import java.util.UUID;
@Tag(
        name = "Administrator",
        description = "Позволяет управлять данными администраторов"
)
@RequestMapping("/api/v1/administrators")
public interface AdministratorAPI {

    @Operation(summary = "Получение данных всех администраторов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    List<AdministratorResponse> findAllAdministrators();

    @Operation(summary = "Создание нового администратора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/new")
    ResponseEntity<HttpStatus> createAdministrator(@RequestBody AdministratorCreateRequest administrator);

    @Operation(summary = "Получение данных администратора по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    AdministratorResponse getAdministrator(@PathVariable @Parameter(description = "Уникальный идентификатор") UUID id);

    @Operation(summary = "Удаление данных администратора по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}/delete")
    ResponseEntity<HttpStatus> deleteAdministrator(@PathVariable @Parameter(description = "Уникальный идентификатор") UUID id);

    @Operation(summary = "Обновление данных администратора по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/update")
    ResponseEntity<HttpStatus> updateAdministrator(@PathVariable @Parameter(description = "Уникальный идентификатор") UUID id, @RequestBody AdministratorUpdateRequest administrator);
}
