package com.udea.skopetest.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.skopetest.persistence.entity.Cuestionario;
import com.udea.skopetest.persistence.repository.ICuestionarioRepository;
import com.udea.skopetest.service.ICuestionarioService;

@Service
public class CuestionarioServiceImpl implements ICuestionarioService {
	
	@Autowired
    private ICuestionarioRepository cuestionarioRepository;

	@Override
	public Cuestionario agregarCuestionario(Cuestionario cuestionario) {
		return cuestionarioRepository.save(cuestionario);
	}

	@Override
	public Cuestionario actualizarCuestionario(Cuestionario cuestionario) {
		return cuestionarioRepository.save(cuestionario);
	}

	@Override
	public Set<Cuestionario> obtenerCuestionarios() {
		return new LinkedHashSet<>(cuestionarioRepository.findAll());
	}

	@Override
	public Cuestionario obtenerCuestionarioId(Long cuestionarioId) {
		return cuestionarioRepository.findById(cuestionarioId).get();
	}

	@Override
	public void eliminarCuestionario(Long cuestionarioId) {
		Cuestionario cuestionario = new Cuestionario();
        cuestionario.setIdCuestionario(cuestionarioId);
        cuestionarioRepository.delete(cuestionario);
		
	}
}
