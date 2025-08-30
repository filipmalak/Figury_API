package Figury;

import java.util.List;

public class Kwadrat implements Figura{
    private Double bok;

    public Kwadrat(List<Double> parametry){
        if(parametry.size() != 1 || parametry.get(0) <= 0){
            throw new IllegalArgumentException("podaj poprawne wartości");
        }

        this.bok = parametry.get(0);
    }

    @Override
    public String getTyp(){
        return "KWADRAT";
    }

    @Override
    public List<Double> getParametry(){
        return List.of(bok);
    }

}


