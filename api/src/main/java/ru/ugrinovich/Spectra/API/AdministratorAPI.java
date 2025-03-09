package ru.ugrinovich.Spectra.API;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerResponse;
import ru.ugrinovich.Spectra.response.Byer.ForAdministratorBuyerWithItemsResponse;
import ru.ugrinovich.Spectra.response.Item.ForAdminOfferResponse;
import ru.ugrinovich.Spectra.response.Item.ItemRemainingResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Administrator",
        description = "Позволяет управлять данными администраторов"
)
@RequestMapping("/api/v1/administrator")
public interface AdministratorAPI {


    @Operation(summary = "Создание нового покупателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/buyers/new")
    ResponseEntity<BuyerResponse> createBuyer(@RequestBody @Valid BuyerCreateRequest buyerCreateRequest);

    @Operation(summary = "Обнновление покупателя по уникальному иденнтификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/buyers/{id}/update")
    ResponseEntity<BuyerResponse> updateBuyer(@PathVariable("id") @Parameter(description = "Уникальный идентификатор")  UUID id, @RequestBody @Valid BuyerUpdateRequest BuyerUpdateRequest);

    @Operation(summary = "Удаление покупателя по уникальному иденнтификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/buyers/{id}/delete")
    ResponseEntity<HttpStatus> deleteBuyer(@PathVariable("id") @Parameter(description = "Уникальный идентификатор")  UUID id);

    @Operation(summary = "Получение данных о всех покупателях")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/buyers")
    ResponseEntity<List<ForAdministratorBuyerWithItemsResponse>> getBuyersWithItems();

    @Operation(summary = "Получение данных о покупателе по уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/buyers/{id}")
    ResponseEntity<ForAdministratorBuyerResponse> getBuyer(@PathVariable("id") @Parameter(description = "Уникальный идентификатор")  UUID id);


    @Operation(summary = "Получение остатка по товарам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("items/remaining")
    ResponseEntity<List<ItemRemainingResponse>> getRemainingItems();

    @Operation(summary = "Массовое добавление товаров.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("items/batch-add")
    ResponseEntity<List<ItemResponse>> batchAddItems(@RequestBody @Valid List<ItemCreateRequest> items);

    @Operation(summary = "Создание нового товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("items/new")
    ResponseEntity<ItemResponse> createItem(@RequestBody @Valid ItemCreateRequest item);

    @Operation(summary = "Получение всех оферов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("items/offers")
    ResponseEntity<List<ForAdminOfferResponse>> findAllOffers();

    @Operation(summary = "Получение данных всех администраторов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    ResponseEntity<List<AdministratorResponse>> findAllAdministrators();

    @Operation(summary = "Создание нового администратора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/new")
    ResponseEntity<AdministratorResponse> createAdministrator(@RequestBody AdministratorCreateRequest administrator);

    @Operation(summary = "Получение данных администратора по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<AdministratorResponse> getAdministrator(@PathVariable @Parameter(description = "Уникальный идентификатор") UUID id);

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
    ResponseEntity<AdministratorResponse> updateAdministrator(@PathVariable @Parameter(description = "Уникальный идентификатор") UUID id, @RequestBody AdministratorUpdateRequest administrator);
}
