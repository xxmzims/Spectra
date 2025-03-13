package ru.ugrinovich.Spectra.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CustomInstantSerializer extends JsonSerializer<Instant> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC);

    @Override
    public void serialize(
            Instant instant,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {

        String formattedDateTime = FORMATTER.format(instant);
        jsonGenerator.writeString(formattedDateTime);
    }
}
