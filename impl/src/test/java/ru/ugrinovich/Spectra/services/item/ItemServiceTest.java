package ru.ugrinovich.Spectra.services.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.repositories.jpa.ItemRepositoryJpa;
import ru.ugrinovich.Spectra.request.Item.ItemType;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepositoryJpa itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    private Item item;


    @BeforeEach
    void setUp(){
        UUID uuid = UUID.randomUUID();
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
}
