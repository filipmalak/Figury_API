import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.GlobalnyHandler;

public class GlobalnyHandlerNegTest {

    @Test
    public void testObsłużInne() {
        Exception exception = new Exception("Testowy błąd");

        GlobalnyHandler handler = new GlobalnyHandler();
        ResponseEntity<String> response = handler.obsłużInne(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Błąd: Testowy błąd"));
    }
}
