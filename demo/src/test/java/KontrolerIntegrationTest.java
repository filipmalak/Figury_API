import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class KontrolerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStworzFigureIntegrationHappyCase() throws Exception {
        String jsonRequest = "{\"typ\": \"KOLO\", \"parametry\": [10.0]}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typ").value("KOLO"))
                .andExpect(jsonPath("$.parametry[0]").value(10.0));
    }

    @Test
    void testStworzFigureIntegrationNegativeCase() throws Exception {
        String jsonRequest = "{\"typ\": \"\", \"parametry\": []}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.typ").value("Typ figury nie może być pusty"))
                .andExpect(jsonPath("$.parametry").value("Musi być przynajmniej jeden parametr"));
    }
}
