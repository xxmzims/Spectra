package ru.ugrinovich.Spectra.response.Byer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BuyerResponse{
    String firstName;

    String secondName;

    int age;

    String email;
}
