package Figury;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StworzFigure {
    private static final Map<String, Function<List<Double>, Figura>> fabrykaFigur = new HashMap<>();

    static{
        fabrykaFigur.put("KWADRAT", Kwadrat::new);
        fabrykaFigur.put("KOLO", Kolo::new);
        fabrykaFigur.put("PROSTOKAT", Prostokat::new);
    }

    public static Figura stworzFigure(String typ, List<Double> parametry){
        Function<List<Double>, Figura> stworz = fabrykaFigur.get(typ.toUpperCase());
        if (stworz == null) {
            throw new IllegalArgumentException("Nieznany typ figury: " + typ);
        }
            System.out.println("ok1");
        return stworz.apply(parametry);
    }

    public static void zarejestrujFigure(String typ, Function<List<Double>, Figura> konstruktor) {
    fabrykaFigur.put(typ.toUpperCase(), konstruktor);
}

}
