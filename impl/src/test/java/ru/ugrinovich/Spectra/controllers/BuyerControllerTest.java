package ru.ugrinovich.Spectra.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.mappers.BuyerMapper;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.mappers.PurchaseHistoryMapper;
import ru.ugrinovich.Spectra.request.Buyer.BuyerCreateRequest;
import ru.ugrinovich.Spectra.request.Buyer.BuyerUpdateRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForAddItemToPurchaseListRequest;
import ru.ugrinovich.Spectra.request.Buyer.ForGetHistoryOfPurchaseRequest;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.request.Item.ItemType;
import ru.ugrinovich.Spectra.request.Item.ItemTypeSort;
import ru.ugrinovich.Spectra.response.Byer.BuyerResponse;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseHistoryResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.buyer.BuyerServiceImpl;
import ru.ugrinovich.Spectra.services.item.ItemServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class BuyerControllerTest {

    @Mock
    private BuyerServiceImpl buyerService;

    @Mock
    private ItemServiceImpl itemService;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private BuyerMapper buyerMapper;

    @Mock
    private PurchaseHistoryMapper purchaseHistoryMapper;

    @InjectMocks
    private BuyerController buyerController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Item item;

    private ItemResponse itemResponse;

    private UUID uuid;

    private BuyerResponse buyerResponse;

    private Buyer buyer;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        buyer = Buyer.builder()
                .id(uuid)
                .age(20)
                .firstName("sasha")
                .secondName("viktorov")
                .age(10)
                .email("xxmzism@gmail.com")
                .build();

        buyerResponse = BuyerResponse.builder()
                .age(20)
                .firstName("sasha")
                .secondName("viktorov")
                .email("xxmzism@gmail.com")
                .build();

        item = Item.builder()
                .id(uuid)
                .name("Iphone")
                .category(ItemType.PHONE)
                .price(100)
                .amount(200)
                .description("sdsdfsdf")
                .serialNumber("3sdsd4234234234")
                .build();

        itemResponse = ItemResponse.builder()
                .id(uuid)
                .name("Iphone")
                .category(ItemType.PHONE)
                .price(100)
                .amount(200)
                .description("sdsdfsdf")
                .serialNumber("3sdsd4234234234")
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(buyerController).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void addItemToPurchaseList() throws Exception {
        ForAddItemToPurchaseListRequest request = ForAddItemToPurchaseListRequest.builder()
                .itemId(UUID.randomUUID())
                .buyerId(UUID.randomUUID())
                .quantity(12)
                .price(12.1)
                .build();

        mockMvc.perform(post("/api/v1/buyers/items/to_purchase_list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status()
                        .isOk());

        verify(buyerService).addItemToPurchaseList(any(ForAddItemToPurchaseListRequest.class));
    }

    @Test
    void getItems_ShouldReturnPageItemResponse() throws Exception {
        ItemFilterRequest request = ItemFilterRequest.builder()
                .limit(10)
                .amount(10)
                .offset(0)
                .endPrice(0.2)
                .startPrice(10.5)
                .category(ItemType.PHONE)
                .order(ItemTypeSort.AMOUNT_ASC)
                .build();

        Page<ItemResponse> pageResponse = new PageImpl<>(List.of(itemResponse),
                PageRequest.of(0, 10),
                2);

        when(itemService.getAllItemWithSpecAndPag(request)).thenReturn(pageResponse);

        mockMvc.perform(post("/api/v1/buyers/items/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(pageResponse)));
    }

    @Test
    void getItem_ById_WhenExists_ShouldReturnItem() throws Exception {
        when(itemService.findById(uuid)).thenReturn(item);
        when(itemMapper.toItemResponse(item)).thenReturn(itemResponse);

        mockMvc.perform(get("/api/v1/buyers/items/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(itemResponse)));

        verify(itemService).findById(any(UUID.class));
    }

    @Test
    void createBuyer_ShouldReturnCreatedBuyer() throws Exception {
        BuyerCreateRequest buyerCreateRequest = BuyerCreateRequest.builder()
                .age(20)
                .firstName("sasha")
                .secondName("vicotorov")
                .email("xxmzims@gmail.com")
                .build();

        when(buyerMapper.toBuyer(buyerCreateRequest)).thenReturn(buyer);
        when(buyerMapper.toBuyerResponse(buyer)).thenReturn(buyerResponse);

        mockMvc.perform(post("/api/v1/buyers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(buyerCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(toJson(buyerResponse)));
    }

    @Test
    void getBuyer_ById_WhenExists_ShouldReturnBuyer() throws Exception {

        when(buyerService.findById(uuid)).thenReturn(buyer);
        when(buyerMapper.toBuyerResponse(buyer)).thenReturn(buyerResponse);

        mockMvc.perform(get("/api/v1/buyers/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(buyerResponse)));
    }

    @Test
    void updateBuyer_WhenExists_ShouldReturnUpdatedBuyer() throws Exception {
        BuyerUpdateRequest buyerUpdateRequest = BuyerUpdateRequest.builder()
                .age(20)
                .firstName("sasha")
                .secondName("viktorov")
                .email("xxmzism@gmail.com")
                .build();

        when(buyerMapper.toBuyerResponse(buyer)).thenReturn(buyerResponse);
        when(buyerMapper.toBuyer(buyerUpdateRequest)).thenReturn(buyer);

        mockMvc.perform(patch("/api/v1/buyers/{id}/update", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(buyerUpdateRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(toJson(buyerResponse)));
    }

    @Test
    void getHistoryOfPleasures_WhenExists_ShouldReturnListOfPleasures() throws Exception{
        UUID puchaseUUID = UUID.randomUUID();
        ForGetHistoryOfPurchaseRequest forGetHistoryOfPurchaseRequest = new ForGetHistoryOfPurchaseRequest(uuid);

        ItemPurchaseHistoryResponse response = ItemPurchaseHistoryResponse.builder()
                .itemId(puchaseUUID)
                .itemId(uuid)
                .serialNumber("sdsdsd")
                .totalPrice(200.2)
                .quantity(10)
                .serialNumber("3sdsd4234234234")
                .build();


        ItemPurchase itemPurchase = ItemPurchase.builder()
                .id(puchaseUUID)
                .buyer(buyer)
                .item(item)
                .quantity(10)
                .totalPrice(200.2)
                .build();

        List<ItemPurchase> purchases = List.of(itemPurchase);

        List<ItemPurchaseHistoryResponse> purchaseHistoryResponse = List.of(response);
        when(buyerService.findHistoryOfPurchases(forGetHistoryOfPurchaseRequest)).thenReturn(purchases);
        when(purchaseHistoryMapper.toResponseList(purchases)).thenReturn(purchaseHistoryResponse);

        mockMvc.perform(post("/api/v1/buyers//get_history_of_pleasures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(forGetHistoryOfPurchaseRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(purchaseHistoryResponse)));

    }

    private <T> String toJson(T o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}
