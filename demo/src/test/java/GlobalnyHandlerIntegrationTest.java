import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Main;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class GlobalnyHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testObsłużWalidacjęIntegration() throws Exception {
        String jsonRequest = "{\"typ\": \"\", \"parametry\": []}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType("application/json")
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.typ").value("Typ figury nie może być pusty"))
                .andExpect(jsonPath("$.parametry").value("Musi być przynajmniej jeden parametr"));
    }
    @Test
    void testObsłużNieznanyTypIntegration() throws Exception {
        // Nieznany typ → IllegalArgumentException z fabryki
        String jsonRequest = "{\"typ\": \"TROJKAT\", \"parametry\": [5.0]}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nieznany typ figury: TROJKAT"));
    }

}
