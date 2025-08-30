import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import Figury.Figura;
import Figury.StworzFigure;

public class StworzFigureTest {

    @Test
    public void testStworzFigureHappyCase() {
        List<Double> parametry = List.of(5.0); 
        Figura figura = StworzFigure.stworzFigure("KWADRAT", parametry);

        assertNotNull(figura);  
        assertEquals("KWADRAT", figura.getTyp());  
    }

    @Test
public void testStworzFigureNegativeCase() {
    List<Double> parametry = List.of(5.0); 

    try {
        StworzFigure.stworzFigure("TRÓJKĄT", parametry); 
        fail("Oczekiwano wyjątku IllegalArgumentException");
    } catch (IllegalArgumentException e) {
        assertEquals("Nieznany typ figury: TRÓJKĄT", e.getMessage());  
    }
}

}
