package ru.ugrinovich.Spectra.response.Byer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class BuyerResponse{
    private String firstName;

    private String secondName;

    private int age;

    private String email;
}
