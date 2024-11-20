package com.udea.skopetest.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.skopetest.persistence.entity.Cuestionario;
import com.udea.skopetest.persistence.entity.Pregunta;

@Repository
public interface IPreguntaRepository extends JpaRepository<Pregunta,Long> {
	
	Set<Pregunta> findByCuestionario(Cuestionario cuestionario);

}
