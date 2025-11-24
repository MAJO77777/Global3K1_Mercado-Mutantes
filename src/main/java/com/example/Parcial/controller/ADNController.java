package com.example.Parcial.controller;

import com.example.Parcial.dto.ADNRequest;
import com.example.Parcial.service.ADNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class ADNController {
    /*
    Maria Jose Muñoz Keim
	Legajo 51005
	Comision 3K10

    NOTA: El código está completamente comentado para explicar su ejecución adecuadamente.
    */

    @Autowired
    private ADNService adnService;

    //método POST isMutant
    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody ADNRequest adnRequest) {
        String[] adn = adnRequest.getSecuencia();  // Recibe el array de strings del request
        boolean esMutante = adnService.isMutant(adn);
        adnService.saveADN(String.join(",", adn), esMutante);
        if (esMutante) {
            System.out.println("ADN Mutante"); //para testear
            return new ResponseEntity<>("El ADN ingresado es de un Mutante", HttpStatus.OK);
        } else {
            System.out.println("ADN Humano"); //para testear
            return new ResponseEntity<>("El ADN ingresado es de un Humano", HttpStatus.FORBIDDEN);
        }
    }
}

