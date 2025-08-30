package Figury;

import java.util.List;

public class Kolo implements Figura{
    private double srednica;

    public Kolo (List<Double> parametry){
        if (parametry == null || parametry.size() != 1) {
            throw new IllegalArgumentException("Koło wymaga jednego parametru: promienia.");
        }
        double r = parametry.get(0);
        if (r <= 0) {
            throw new IllegalArgumentException("Promień koła musi być dodatni.");
        }
        this.srednica = parametry.get(0);
    }

    @Override
    public String getTyp() {
        return "KOLO";
    }

    @Override
    public List<Double> getParametry() {
        return List.of(srednica);
    }
    
}
