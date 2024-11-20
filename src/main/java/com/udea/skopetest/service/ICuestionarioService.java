package com.udea.skopetest.service;

import java.util.Set;

import com.udea.skopetest.persistence.entity.Cuestionario;

public interface ICuestionarioService {
	
	Cuestionario agregarCuestionario(Cuestionario cuestionario);

	Cuestionario actualizarCuestionario(Cuestionario cuestionario);

    Set<Cuestionario> obtenerCuestionarios();

    Cuestionario obtenerCuestionarioId(Long cuestionarioId);

    void eliminarCuestionario(Long cuestionarioId);

}
