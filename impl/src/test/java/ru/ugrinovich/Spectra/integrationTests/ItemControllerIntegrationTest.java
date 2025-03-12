package ru.ugrinovich.Spectra.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.ugrinovich.Spectra.mappers.ItemMapper;
import ru.ugrinovich.Spectra.request.Item.ItemCreateRequest;
import ru.ugrinovich.Spectra.request.Item.ItemType;
import ru.ugrinovich.Spectra.response.Item.ItemResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private ItemCreateRequest createRequest;

    @BeforeEach
    void setUp() {
         createRequest = ItemCreateRequest.builder()
                .name("name")
                .serialNumber("serialNumber")
                .category(ItemType.PHONE)
                .price(1)
                .amount(1)
                .description("description")
                .build();

    }

    @Test
    void createItem_ValidRequest_ReturnsCreated() throws Exception {

        String res = mockMvc.perform(post("/api/v1/items/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

         ItemResponse itemResponse = objectMapper.readValue(res, ItemResponse.class);
         itemResponse.setId(null);

       assertThat(itemMapper.toItemResponse(itemMapper.toItem(createRequest))).isEqualTo(itemResponse);

    }

}
