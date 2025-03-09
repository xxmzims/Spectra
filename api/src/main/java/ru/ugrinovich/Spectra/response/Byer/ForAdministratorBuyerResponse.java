package ru.ugrinovich.Spectra.response.Byer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ForAdministratorBuyerResponse {

    UUID buyerId;

    String firstName;

    String secondName;

    int age;

    String email;

    Instant createAt;

    Instant updateAt;

}
