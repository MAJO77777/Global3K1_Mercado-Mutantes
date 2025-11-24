package com.example.Parcial.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ADNRequest {
    /*
    Maria Jose Muñoz Keim
	Legajo 51005
	Comision 3K10


    NOTA: El código está completamente comentado para explicar su ejecución adecuadamente.
    */

    // La clase ADNRequest sirve para encapsular y manejar el array de Strings correctamente
    // (ya que en la base de datos H2, el atributo es un String, no un array)
    private String[] secuencia;

    // sí, estos comentarios son reales y los hice a mano, re divertido!
}