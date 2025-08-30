package Figury;

import java.util.List;

public class Prostokat implements Figura{
    private double bok1;
    private double bok2;

    public Prostokat(List<Double> parametry) {
        if (parametry.size() != 2) {
            throw new IllegalArgumentException("Podaj dokładnie 2 parametry: bok1 i bok2.");
        }
        if (parametry.get(0) <= 0 || parametry.get(1) <= 0) {
            throw new IllegalArgumentException("Oba boki prostokąta muszą być dodatnie.");
        }
        this.bok1 = parametry.get(0);
        this.bok2 = parametry.get(1);
    }

    @Override
    public String getTyp() {
        return "PROSTOKAT";
    }

    @Override
    public List<Double> getParametry() {
        return List.of(bok1, bok2);
    }
    
    
}
