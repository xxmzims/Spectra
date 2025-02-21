package ru.ugrinovich.Spectra.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.API.AdministratorAPI;
import ru.ugrinovich.Spectra.entities.Administrator;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.mappers.AdministratorMapper;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;
import ru.ugrinovich.Spectra.services.administrator.AdministratorService;
import ru.ugrinovich.Spectra.services.item.ItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdministratorController implements AdministratorAPI {

    private final AdministratorMapper administratorMapper;
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final AdministratorService administratorsService;

    public ResponseEntity<List<AdministratorResponse>> findAllAdministrators() {
        List<Administrator> administrators = administratorsService.findAllAdministrators();
       List<AdministratorResponse> administratorsResponse =  administratorMapper.toAdministratorResponse(administrators);
        log.info("Найдены администраторы с id {}", administrators.stream().map(Administrator::getAdminId).collect(Collectors.toList()));
        return ResponseEntity.ok(administratorsResponse);
    }

    @Override
    public ResponseEntity<ItemResponse> createItem(ItemCreateRequest itemCreateRequest) {
        Item item = itemMapper.toItem(itemCreateRequest);
        itemService.save(item);
        log.info("Создан товар с id {}" ,item.getId());
        return ResponseEntity.ok(itemMapper.toItemResponse(item));
    }

    public ResponseEntity<AdministratorResponse> createAdministrator(AdministratorCreateRequest administratorCreateRequest) {
        Administrator administrator = administratorMapper.toAdministrator(administratorCreateRequest);
        administratorsService.save(administrator);
        AdministratorResponse administratorResponse = administratorMapper.toAdministratorResponse(administrator);
        log.info("Создан администратор с id {}", administrator.getAdminId());
        return new ResponseEntity<>(administratorResponse, CREATED);
    }

    public ResponseEntity<AdministratorResponse> getAdministrator(UUID id) {
        AdministratorResponse administratorResponse = administratorMapper.toAdministratorResponse(administratorsService.findById(id));
        log.info("Найден администратор с id {}", id);
        return  ResponseEntity.ok(administratorResponse);
    }

    public ResponseEntity<HttpStatus> deleteAdministrator(UUID id) {
        administratorsService.deleteById(id);
        log.info("Удален администратор с id {}", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<AdministratorResponse> updateAdministrator(UUID id, AdministratorUpdateRequest administratorUpdateRequest) {
        Administrator administrator = administratorMapper.toAdministrator(administratorUpdateRequest);
        administrator = administratorsService.updateById(id, administrator);
        AdministratorResponse administratorResponse = administratorMapper.toAdministratorResponse(administrator);
        log.info("Обновлены данные администратора с id {}", id);
        return new  ResponseEntity<>(administratorResponse, ACCEPTED);
    }

}
