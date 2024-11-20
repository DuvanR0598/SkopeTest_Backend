package com.udea.skopetest.service;

import java.util.Set;

import com.udea.skopetest.persistence.entity.Examen;

public interface IExamenService {
	
	Examen agregarExamen(Examen examen);

	Examen actualizarExamen(Examen examen);

    Set<Examen> obtenerExamenes();

    Examen obtenerExamenId(Long examenId);

    void eliminarExamen(Long examenId);

}
