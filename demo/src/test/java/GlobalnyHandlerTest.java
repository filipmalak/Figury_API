import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.GlobalnyHandler;

import Figury.ŻądanieFigury;

class GlobalnyHandlerTest {

    @Test
    void testObsluzWalidacje() {
        ŻądanieFigury target = new ŻądanieFigury();
        BindingResult br = new BeanPropertyBindingResult(target, "żądanieFigury");

        br.rejectValue("typ", "NotBlank", "Typ figury nie może być pusty");

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, br);

        GlobalnyHandler handler = new GlobalnyHandler();
        ResponseEntity<Map<String, String>> resp = handler.obsłużWalidację(ex);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        assertTrue(resp.getBody().containsKey("typ"));
        assertEquals("Typ figury nie może być pusty", resp.getBody().get("typ"));
    }
}
