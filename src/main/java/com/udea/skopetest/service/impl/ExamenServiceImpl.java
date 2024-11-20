package com.udea.skopetest.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.skopetest.persistence.entity.Examen;
import com.udea.skopetest.persistence.repository.IExamenRepository;
import com.udea.skopetest.service.IExamenService;

@Service
public class ExamenServiceImpl implements IExamenService {
	
	@Autowired
    private IExamenRepository examenRepository;

	@Override
	public Examen agregarExamen(Examen examen) {
		return examenRepository.save(examen);
	}

	@Override
	public Examen actualizarExamen(Examen examen) {
		return examenRepository.save(examen);
	}

	@Override
	public Set<Examen> obtenerExamenes() {
		return new LinkedHashSet<>(examenRepository.findAll());
	}

	@Override
	public Examen obtenerExamenId(Long examenId) {
		return examenRepository.findById(examenId).get();
	}

	@Override
	public void eliminarExamen(Long examenId) {
		Examen examen = new Examen();
        examen.setIdExamen(examenId);
        examenRepository.delete(examen);
	}

}
