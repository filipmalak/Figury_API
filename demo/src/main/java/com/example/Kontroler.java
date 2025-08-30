package com.example;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Figury.Figura;
import Figury.StworzFigure;
import Figury.ŻądanieFigury;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/shapes")
public class Kontroler{
    
        private final List<Figura> figury = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> stworzFigure(@Valid @RequestBody ŻądanieFigury żądanie) {
        try {
            Figura figura = StworzFigure.stworzFigure(
                żądanie.getTyp(),
                żądanie.getParametry()
            );
            System.out.println("ok");

                        figury.add(figura); 

            return ResponseEntity.ok(figura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("błąd");
        }
    }

        @GetMapping
    public ResponseEntity<List<Figura>> wszystkieFigury(
            @RequestParam(value = "typ", required = false) String typ) {

        List<Figura> result;
        if (typ == null || typ.isEmpty()) {
            result = figury; 
        } else {
            result = figury.stream()
                    .filter(f -> f.getTyp().equalsIgnoreCase(typ))
                    .toList(); 
        }
        return ResponseEntity.ok(result);
    }
}
