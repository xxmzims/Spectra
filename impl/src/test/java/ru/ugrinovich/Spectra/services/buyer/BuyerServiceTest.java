package ru.ugrinovich.Spectra.services.buyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.exceptions.is_already_exist.EmailAdressIsAlreadyExistException;
import ru.ugrinovich.Spectra.exceptions.not_found.BuyerNotFoundException;
import ru.ugrinovich.Spectra.repositories.jpa.BuyerRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyerServiceTest {

    @InjectMocks
    private BuyerServiceImpl buyerService;

    @Mock
    private BuyerRepositoryJpa buyerRepository;

    private Buyer buyer;

    private UUID uuid;

    @BeforeEach
    void setUp(){
        uuid = UUID.randomUUID();
        buyer = Buyer.builder()
                .id(uuid)
                .age(18)
                .firstName("Ivan")
                .secondName("Ivanov")
                .email("xxmzims@gmail.com")
                .build();
    }
    @Test
    void findAllBuyers_ShouldReturnAllBuyers(){

        List<Buyer> expected = List.of(buyer);

        when(buyerRepository.findAll())
                .thenReturn(expected);

        assertEquals(buyerService.findAllBuyers(), expected);

        verify(buyerRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnBuyer(){

        when(buyerRepository.findById(uuid))
                .thenReturn(Optional.of(buyer));

        assertEquals(buyerService.findById(uuid), buyer);

        verify(buyerRepository).findById(uuid);
    }

    @Test
    void findById_WhenNotExists_ShouldThrowException(){

        when(buyerRepository.findById(uuid))
                .thenReturn(Optional.empty());

        assertThrows(BuyerNotFoundException.class,
                () -> buyerService.findById(uuid));

        verify(buyerRepository).findById(uuid);
    }

    @Test
    void save_WhenEmailIsUnique_ShouldSaveBuyer() {
        // Arrange

        when(buyerRepository.findBuyerByEmail("erererererre@email.com"))
                .thenReturn(Optional.empty());

        // Act
        buyerService.save(buyer);

        // Assert
        verify(buyerRepository).findBuyerByEmail("erererererre@email.com");
        verify(buyerRepository).save(buyer);
    }

    @Test
    void save_WhenEmailExists_ShouldThrowException() {
        when(buyerRepository.findBuyerByEmail("xxmzims@gmail.com"))
                .thenReturn(Optional.of(buyer));

        assertThrows(EmailAdressIsAlreadyExistException.class,
                () -> buyerService.save(buyer));

        verify(buyerRepository, never()).save(any());
    }

}
