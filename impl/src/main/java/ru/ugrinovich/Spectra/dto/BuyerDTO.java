package ru.ugrinovich.Spectra.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerDTO {

    private UUID uuid;

    private String firstName;

    private String secondName;

    private int age;

    private String email;

}
