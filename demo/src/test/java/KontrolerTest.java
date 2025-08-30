import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.GlobalnyHandler;
import com.example.Kontroler;

class KontrolerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        var validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new Kontroler())
                .setControllerAdvice(new GlobalnyHandler())   
                .setValidator(validator)                     
                .build();
    }

    @Test
    void testStworzFigureHappyCase() throws Exception {
        String json = "{\"typ\": \"KWADRAT\", \"parametry\": [5.0]}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typ").value("KWADRAT"))
                .andExpect(jsonPath("$.parametry[0]").value(5.0));
    }

    @Test
    void testStworzFigureNegativeCaseWalidacja() throws Exception {
        String json = "{\"typ\": \"\", \"parametry\": []}";

        mockMvc.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.typ").value("figura nie może być pusta"))
                .andExpect(jsonPath("$.parametry").value("Musi być przynajmniej jeden parametr"));
    }
}
