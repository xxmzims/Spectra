package ru.ugrinovich.Spectra.response.Byer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class BuyerResponse {
    private String firstName;

    private String secondName;

    private int age;

    private String email;
}
