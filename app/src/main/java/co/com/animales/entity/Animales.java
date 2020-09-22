package co.com.animales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animales {

    private int imagen;
    private String nombre;
    private String descripcion;
    private int sonido;
}
