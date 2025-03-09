package ru.ugrinovich.Spectra.services.administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ugrinovich.Spectra.entities.Administrator;
import ru.ugrinovich.Spectra.exceptions.not_found.AdministratorNotFoundException;
import ru.ugrinovich.Spectra.repositories.jpa.AdministratorRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AdministratorServiceTest {
    private Administrator administrator;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        administrator = Administrator.builder()
                .adminId(uuid)
                .name("Sasha")
                .build();
    }

    @Mock
    private AdministratorRepositoryJpa administratorRepository;

    @InjectMocks
    private AdministratorServiceImpl administratorService;

    @Test
    void findAllAdministrators_ShouldReturnAllAdmins() {

        List<Administrator> expected = List.of(
                administrator);

        when(administratorRepository.findAll()).thenReturn(expected);

        List<Administrator> result = administratorService.findAllAdministrators();

        assertEquals(expected, result);

        verify(administratorRepository).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnAdmin() {

        when(administratorRepository.findById(uuid)).thenReturn(Optional.of(administrator));

        Administrator result = administratorService.findById(uuid);

        assertEquals(administrator, result);

        verify(administratorRepository).findById(uuid);
    }

    @Test
    void findById_WhenNotExists_ShouldThrowException() {

        Optional<Administrator> expected = Optional.empty();

        when(administratorRepository.findById(uuid)).thenReturn(expected);

        assertThrows(AdministratorNotFoundException.class, () -> administratorService.findById(uuid));

        verify(administratorRepository).findById(uuid);
    }

    @Test
    void save_ShouldCallRepositorySave() {

        administratorService.save(administrator);
        verify(administratorRepository).save(administrator);
    }


    @Test
    void delete_WhenExists_ShouldCallRepositoryDelete() {

        when(administratorRepository.findById(uuid))
                .thenReturn(Optional.of(administrator));

        administratorService.deleteById(uuid);

        verify(administratorRepository).findById(uuid);
        verify(administratorRepository).deleteById(uuid);

    }

    @Test
    void delete_WhenNotExists_ShouldThrowException() {
        when(administratorRepository.findById(uuid))
                .thenReturn(Optional.empty());

        assertThrows(AdministratorNotFoundException.class,
                () -> administratorService.deleteById(uuid));

        verify(administratorRepository).findById(uuid);
        verify(administratorRepository, never()).deleteById(any());
    }

    @Test
    void update_WhenExits_ShouldReturnUpdatedAdministrator() {
        Administrator updated = Administrator.builder()
                .name("dsdsd")
                .build();

        when(administratorRepository.findById(uuid))
                .thenReturn(Optional.of(administrator));

        administratorService.updateById(uuid, updated);

        assertEquals(administrator.getName(), updated.getName());
        assertEquals(administrator.getAdminId(), uuid);

        verify(administratorRepository).findById(uuid);
        verify(administratorRepository).save(administrator);
    }

    @Test
    void update_WhenNotExists_ShouldThrowException() {

        when(administratorRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(AdministratorNotFoundException.class,
                () -> administratorService.updateById(uuid, new Administrator()));

        verify(administratorRepository).findById(uuid);
        verify(administratorRepository, never()).save(any());
    }

}
