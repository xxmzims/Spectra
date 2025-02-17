package ru.ugrinovich.Spectra.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.API.AdministratorAPI;
import ru.ugrinovich.Spectra.dto.AdministratorDTO;
import ru.ugrinovich.Spectra.entity.Administrator;
import ru.ugrinovich.Spectra.mapper.AdministratorMapper;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;
import ru.ugrinovich.Spectra.services.administrator.AdministratorService;
import ru.ugrinovich.Spectra.services.administrator.AdministratorServiceLocalImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdministratorController implements AdministratorAPI {

    private final AdministratorMapper administratorMapper;
    private final AdministratorService administratorsService;

    public List<AdministratorResponse> findAllAdministrators() {
        List<Administrator> administrators = administratorsService.findAllAdministrators();
       List<AdministratorResponse> administratorResponse =  administratorMapper.toAdministratorResponse(administrators);
        log.info("Найдены администраторы с id {}", administrators.stream().map(Administrator::getAdminId).collect(Collectors.toList()));
        return administratorResponse;
    }

    public ResponseEntity<HttpStatus> createAdministrator(AdministratorCreateRequest administratorCreateRequest) {
        AdministratorDTO administratorDTO = administratorMapper.toAdministratorDTO(administratorCreateRequest);
        Administrator administrator = administratorMapper.toAdministrator(administratorDTO);
        administratorsService.save(administrator);
        log.info("Создан администратор с id {}", administrator.getAdminId());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public AdministratorResponse getAdministrator(UUID id) {
        AdministratorDTO administratorDTO = administratorMapper.toAdministratorDto(administratorsService.findById(id));
        log.info("Найден администратор с id {}", id);
        return administratorMapper.toAdministratorResponse(administratorDTO);
    }

    public ResponseEntity<HttpStatus> deleteAdministrator(UUID id) {
        administratorsService.deleteById(id);
        log.info("Удален администратор с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> updateAdministrator(UUID id, AdministratorUpdateRequest administratorUpdateRequest) {
        AdministratorDTO administratorDTO = administratorMapper.toAdministratorDTO(administratorUpdateRequest);
        Administrator administrator = administratorMapper.toAdministrator(administratorDTO);
        administratorsService.updateById(id, administrator);
        log.info("Обновлены данные клиента с id {}", id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
