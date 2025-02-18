package ru.ugrinovich.Spectra.API;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemUpdateRequest;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Item",
        description = "Позволяет управлять данными товаров"
)
@RequestMapping("/api/v1/items")
public interface ItemAPI {

    @Operation(summary = "Получение данных всех товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    List<ItemResponse> getAllItems();

    @Operation(summary = "Создание нового товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/new")
    ResponseEntity<ItemResponse> createItem(@RequestBody ItemCreateRequest item);

    @Operation(summary = "Получение данных товара по уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{itemId}")
    ResponseEntity<ItemResponse> getItem(@PathVariable("itemId") @Parameter(description = "Уникальный идентификатор") UUID itemId);

    @Operation(summary = "Обновление данных товара по уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{itemId}/update")
    ResponseEntity<ItemResponse> updateItem(@PathVariable("itemId") @Parameter(description = "Уникальный идентификатор") UUID itemId, @RequestBody ItemUpdateRequest item);

    @Operation(summary = "Удаление данных товара по уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{itemId}/delete")
    ResponseEntity<HttpStatus> deleteItem(@PathVariable("itemId") @Parameter(description = "Уникальный идентификатор") UUID itemId);

    @Operation(summary = "Получение данных товара по серийному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/serial/{serialNumber}")
    ResponseEntity<ItemResponse> getItem(@PathVariable("serialNumber") @Parameter(description = "Серийный номер товара") String serialNumber);
}
