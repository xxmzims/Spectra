package ru.ugrinovich.Spectra.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.API.AdministratorAPI;
import ru.ugrinovich.Spectra.entity.Administrator;
import ru.ugrinovich.Spectra.services.administrator.AdministratorServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AdministratorController implements AdministratorAPI {

    private final AdministratorServiceImpl administratorsService;

    public List<Administrator> findAllAdministrators(){
        return administratorsService.findAllAdministrators();
    }

    public ResponseEntity<HttpStatus> createAdministrator( Administrator administrator){
        administratorsService.save(administrator);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public Administrator getAdministrator( UUID id){
        return administratorsService.findById(id);
    }

    public ResponseEntity<HttpStatus> deleteAdministrator(UUID id){
        administratorsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HttpStatus> updateAdministrator( UUID id,  Administrator administrator){
        administratorsService.updateById(id, administrator);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
