package ru.ugrinovich.Spectra.API;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseStatus;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.response.Item.ItemType;
import ru.ugrinovich.Spectra.response.Item.ItemViewStatus;

import java.util.List;
import java.util.UUID;
@Tag(
        name = "Buyer",
        description = "Позволяет управлять данными покупателей"
)
@RequestMapping("/api/v1/buyers")
public interface BuyerAPI {
    @Operation(summary = "Получение данных всех покупателей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    ResponseEntity<List<BuyerResponse>> findAllBuyers();

    @Operation(summary = "Получение данных с использованием пагинации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/items/get_with_pagination")
    ResponseEntity<Page<ItemResponse>> getItems(@RequestParam("offset") @Parameter(description = "Номер страницы") Integer offset,
                                                @RequestParam("limit") @Parameter( description = "Количество элементов на странице") Integer limit);

    @Operation(summary = "Поиск по критериям товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping("/items/get_with_filter")
    public ResponseEntity<List<ItemResponse>> searchItems(@RequestParam(required = false) @Parameter(description = "Категория товара") ItemType category,
                                                          @RequestParam(required = false) @Parameter(description = "Статус просмотра товара")ItemViewStatus viewStatus,
                                                          @RequestParam(required = false) @Parameter(description = "Статус покупки товара") ItemPurchaseStatus itemPurchaseStatus,
                                                          @RequestParam(required = false) @Parameter(description = "Минимальное количество товара") Integer amount,
                                                          @RequestParam(required = false) @Parameter(description = "Минимальная цена товара") Double startPrice,
                                                          @RequestParam(required = false) @Parameter(description = "Максимальная цена товара") Double endPrice);

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

    @Operation(summary = "Присвоение покупателю(обращаемся по его уникальному идентификатору) товара по уникальному идентификатору товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{buyerId}/assign_item/{itemId}")
    ResponseEntity<BuyerResponse> assignItem(@PathVariable("buyerId")
                                          @Parameter(description = "Уникальный идентификатор покупателя") UUID buyerId,
                                          @PathVariable("itemId")
                                          @Parameter(description = "Уникальный идентификатор товара") UUID itemId);

}
