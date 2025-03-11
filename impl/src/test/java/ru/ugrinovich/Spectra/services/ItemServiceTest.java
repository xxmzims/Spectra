package ru.ugrinovich.Spectra.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.repositories.jpa.ItemRepositoryJpa;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.request.Item.ItemType;
import ru.ugrinovich.Spectra.request.Item.ItemTypeSort;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.item.ItemServiceImpl;
import ru.ugrinovich.Spectra.specification.ItemSpecification;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemSpecification itemSpecification;

    @Mock
    private ItemRepositoryJpa itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemServiceImpl itemService;


    private UUID uuid;

    private Item item;


    @BeforeEach
    void setUp(){
        uuid = UUID.randomUUID();
        item = Item.builder()
                .id(uuid)
                .name("Huawei")
                .category(ItemType.PHONE)
                .amount(2)
                .description("SmartPhone for using")
                .price(20.4)
                .serialNumber("lfnsdkj6565fbsf")
                .build();
    }

    @Test
    void findAll_ShouldReturnAllItems(){

        List<Item> items = List.of(item);

        when(itemRepository.findAll()).thenReturn(items);

        assertEquals(itemService.getAllItems(), items);

        verify(itemRepository).findAll();
    }

    @Test
    void findAllWithSpecAndPag_ShouldReturnPageResponse() {
        ItemFilterRequest filter = ItemFilterRequest.builder()
                .order(ItemTypeSort.AMOUNT_ASC)
                .limit(1)
                .offset(10)
                .startPrice(0.0)
                .endPrice(500.0)
                .category(ItemType.PHONE)
                .build();

        Specification<Item> mockSpec = mock(Specification.class);
        PageRequest pageRequest = PageRequest.of(
                filter.getOffset(),
                filter.getLimit(),
                filter.getOrder().getSortValue()
        );
        List<Item> mockItems = List.of(new Item(), new Item());

        Page<Item> mockPage = new PageImpl<>(
                mockItems,
                pageRequest,
                mockItems.size()
        );

        List<ItemResponse> expectedResponse = mockItems.stream()
                .map(item -> new ItemResponse())
                .collect(Collectors.toList());

        when(itemSpecification.toSpecForAllItems(filter)).thenReturn(mockSpec);
        when(itemRepository.findAll(mockSpec, pageRequest)).thenReturn(mockPage);
        when(itemMapper.toItemResponse(any(Item.class))).thenReturn(new ItemResponse());

        Page<ItemResponse> result = itemService.getAllItemWithSpecAndPag(filter);

        assertThat(result.getContent()).hasSize(mockItems.size());
        assertThat(result.getTotalElements()).isEqualTo(mockItems.size());
        assertThat(result.getPageable()).isEqualTo(mockPage.getPageable());

        verify(itemSpecification).toSpecForAllItems(filter);
        verify(itemRepository).findAll(mockSpec, pageRequest);
        verify(itemMapper, times(mockItems.size())).toItemResponse(any(Item.class));
    }
}
