package com.udea.skopetest.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.skopetest.persistence.entity.Cuestionario;
import com.udea.skopetest.persistence.entity.Pregunta;
import com.udea.skopetest.persistence.repository.IPreguntaRepository;
import com.udea.skopetest.service.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService {
	
	@Autowired
    private IPreguntaRepository preguntaRepository;

	@Override
	public Pregunta agregarPregunta(Pregunta pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public Pregunta actualizarPregunta(Pregunta pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public Set<Pregunta> obtenerPreguntas() {
		return (Set<Pregunta>) preguntaRepository.findAll();
	}

	@Override
	public Pregunta obtenerPreguntaId(Long preguntaId) {
		return preguntaRepository.findById(preguntaId).get();
	}

	@Override
	public Set<Pregunta> obtenerPreguntasDelCuestionario(Cuestionario cuestionario) {
		return preguntaRepository.findByCuestionario(cuestionario);
	}

	@Override
	public void eliminarPreguntaId(Long preguntaId) {
		Pregunta pregunta = new Pregunta();
        pregunta.setIdPregunta(preguntaId);
        preguntaRepository.delete(pregunta);
	}

}
