package Figury;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ŻądanieFigury {
    @NotBlank(message = "Typ figury nie może być pusty")
    private String typ;

    @NotNull(message = "Lista parametrów jest wymagana")
    @Size(min = 1, message = "Musi być przynajmniej jeden parametr")
    private List<Double> parametry;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public List<Double> getParametry() {
        return parametry;
    }

    public void setParametry(List<Double> parametry) {
        this.parametry = parametry;
    }
}
