package ru.ugrinovich.Spectra.mappers;

import org.mapstruct.Mapper;
import ru.ugrinovich.Spectra.dto.AdministratorDTO;
import ru.ugrinovich.Spectra.entities.Administrator;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorCreateRequest;
import ru.ugrinovich.Spectra.request.Administrator.AdministratorUpdateRequest;
import ru.ugrinovich.Spectra.response.Administrator.AdministratorResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    AdministratorDTO toAdministratorDto(Administrator administrator);

    List<AdministratorResponse> toAdministratorResponse(List<Administrator> administrators);

    Administrator toAdministrator(AdministratorDTO administratorDTO);

    Administrator toAdministrator(AdministratorUpdateRequest administratorUpdateRequest);

    Administrator toAdministrator(AdministratorCreateRequest administratorCreateRequest);

    AdministratorResponse toAdministratorResponse(Administrator administrator);

    AdministratorResponse toAdministratorResponse(AdministratorDTO administratorDTO);
}
