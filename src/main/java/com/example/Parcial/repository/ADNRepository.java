package com.example.Parcial.repository;

import com.example.Parcial.model.ADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADNRepository extends JpaRepository<ADN, Long> {
    /*
	 Maria Jose Muñoz Keim
	Legajo 51005
	Comision 3K10

	 */
    long countByEsMutante(boolean esMutante);


}
