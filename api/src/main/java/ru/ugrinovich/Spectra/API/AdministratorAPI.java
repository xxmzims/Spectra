package ru.ugrinovich.Spectra.API;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/administrators")
public interface AdministratorAPI {

    @GetMapping()
    List<AdministratorResponse> findAllAdministrators();

    @PostMapping("/new")
    ResponseEntity<HttpStatus> createAdministrator(@RequestBody AdministratorCreateRequest administrator);

    @GetMapping("/{id}")
    AdministratorResponse getAdministrator(@PathVariable UUID id);

    @DeleteMapping("/{id}/delete")
    ResponseEntity<HttpStatus> deleteAdministrator(@PathVariable UUID id);

    @PatchMapping("/{id}/update")
    ResponseEntity<HttpStatus> updateAdministrator(@PathVariable UUID id, @RequestBody AdministratorUpdateRequest administrator);
}
