package ru.ugrinovich.Spectra.response.Administrator;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AdministratorResponse {

    String name;

}
