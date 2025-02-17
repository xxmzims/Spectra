package ru.ugrinovich.Spectra.request.Administrator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class AdministratorUpdateRequest {
    private String name;
}
