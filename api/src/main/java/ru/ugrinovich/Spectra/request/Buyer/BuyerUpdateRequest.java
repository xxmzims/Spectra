package ru.ugrinovich.Spectra.request.Buyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class BuyerUpdateRequest {

    private String firstName;

    private String secondName;

    private int age;

    private String email;
}
