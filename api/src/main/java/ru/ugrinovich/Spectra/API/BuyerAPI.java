package ru.ugrinovich.Spectra.API;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.request.Item.*;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.validation.annotations.EnumValidate;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Buyer",
        description = "Позволяет управлять данными покупателей"
)
@RequestMapping("/api/v1/buyers")
public interface BuyerAPI {
    @Operation(summary = "Запрос на добавление в список покупок товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping("/items/add_to_purchase_list")
    ResponseEntity<HttpStatus> addItemToPurchaseList(ForAddItemToPurchaseListRequest forAddItemToPurchaseListRequest);

    @Operation(summary = "Получение данных всех покупателей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    ResponseEntity<List<BuyerResponse>> findAllBuyers();

    @Operation(summary = "Получение данных товара для покупателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/items/{id}")
    ResponseEntity<ItemResponse> getItem(@PathVariable(value = "id") @Parameter(description = "Уникальный идентификатор товара") UUID id);

    @Operation(summary = "Получение данных товаров с сортировкой, фильтрацией и пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/items/get")
    ResponseEntity<Page<ItemResponse>> getItems(@Valid @RequestBody ItemFilterRequest itemFilterRequest);

    @Operation(summary = "Создание покупателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/new")
    ResponseEntity<BuyerResponse> createBuyer(@RequestBody BuyerCreateRequest buyer);

    @Operation(summary = "Получение данных покупателя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{buyerId}")
    ResponseEntity<BuyerResponse> getBuyer(@PathVariable("buyerId") @Parameter(description = "Уникальный идентификатор покупателя") UUID buyerId);

    @Operation(summary = "Удаление данных покупателя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{buyerId}/delete")
    ResponseEntity<HttpStatus> deleteBuyer(@PathVariable("buyerId") @Parameter(description = "Уникальный идентификатор покупателя") UUID buyerId);

    @Operation(summary = "Обновление данных покупателя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{buyerId}/update")
    ResponseEntity<BuyerResponse> updateBuyer(@PathVariable("buyerId") @Parameter(description = "Уникальный идентификатор покупателя") UUID buyerId, @RequestBody BuyerUpdateRequest buyer);

    @Operation(summary = "Получения списка покупок в зависимости от статуса покупки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/get_history_of_pleasures")
    ResponseEntity<List<ItemResponse>> getHistoryOfPleasures(@RequestBody ForGetHistoryOfPurchaseRequest request);
}
