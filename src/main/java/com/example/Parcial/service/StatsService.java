package com.example.Parcial.service;

import com.example.Parcial.model.Stats;
import com.example.Parcial.repository.ADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatsService {

     /*
	Maria Jose Muñoz Keim
	Legajo 51005
	Comision 3K10

	 */

    //inyección de dependencia
    @Autowired
    private ADNRepository adnRepository;

    public Stats getStats() {
        long contadorADNMutante = adnRepository.countByEsMutante(true);
        long contadorADNHumano = adnRepository.countByEsMutante(false);
        double ratio;
        if (contadorADNHumano > 0) {
            ratio = (double)contadorADNMutante / contadorADNHumano;
        } else{
            ratio = 0;
        }
        return new Stats(contadorADNMutante, contadorADNHumano, ratio);
    }
}