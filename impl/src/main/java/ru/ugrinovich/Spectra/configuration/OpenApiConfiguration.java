package ru.ugrinovich.Spectra.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

// http://localhost:8080/swagger-ui/index.html
@OpenAPIDefinition(
        info = @Info(
                title = "Internet Shop Api",
                description = "Shop system", version = "1.0.0",
                contact = @Contact(
                        name = "Ugrinovich Maxim",
                        email = "xxmzims@gmail.com"
                )
        )
)
public class OpenApiConfiguration {
}
