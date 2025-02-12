package ru.ugrinovich.Spectra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ugrinovich.Spectra.models.Administrator;
import ru.ugrinovich.Spectra.services.AdministratorsService;

import java.util.List;
@RestController
@RequestMapping("/administrators")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorsService administratorsService;

    @GetMapping()
    private List<Administrator> findAllAdministrators(){
        return administratorsService.findAllAdministrators();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createAdministrator(@RequestBody Administrator administrator){
        administratorsService.save(administrator);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Administrator getAdministratorById(@PathVariable int id){
        return administratorsService.findById(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteAdminById(@PathVariable int id){
        administratorsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{id}/update")
    public ResponseEntity<HttpStatus> updateById(@PathVariable int id, @RequestBody Administrator administrator){
        administratorsService.updateById(id, administrator);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
