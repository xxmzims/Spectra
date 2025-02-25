package ru.ugrinovich.Spectra.response.Byer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ForAdministratorBuyerResponse {
    private UUID buyerId;

    private String firstName;

    private String secondName;

    private int age;

    private String email;

    private Instant createAt;

    private Instant updateAt;

}
