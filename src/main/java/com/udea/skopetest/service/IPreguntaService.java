package com.udea.skopetest.service;

import java.util.Set;

import com.udea.skopetest.persistence.entity.Cuestionario;
import com.udea.skopetest.persistence.entity.Pregunta;

public interface IPreguntaService {

	Pregunta agregarPregunta(Pregunta pregunta);

    Pregunta actualizarPregunta(Pregunta pregunta);

    Set<Pregunta> obtenerPreguntas();

    Pregunta obtenerPreguntaId(Long preguntaId);

    Set<Pregunta> obtenerPreguntasDelCuestionario(Cuestionario cuestionario);

    void eliminarPreguntaId(Long preguntaId);
}
