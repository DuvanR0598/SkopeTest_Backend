package com.udea.skopetest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.skopetest.persistence.entity.Cuestionario;
import com.udea.skopetest.persistence.entity.Pregunta;
import com.udea.skopetest.service.ICuestionarioService;
import com.udea.skopetest.service.IPreguntaService;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {
	
	@Autowired
    private IPreguntaService preguntaService;
	
	@Autowired
    private ICuestionarioService cuestionarioService;
	
	@PostMapping("/guardar")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }
	
	@PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }
	
	@GetMapping("/cuestionario/{cuestionarioId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("cuestionarioId") Long cuestionarioId){
        Cuestionario cuestionario = cuestionarioService.obtenerCuestionarioId(cuestionarioId);
        Set<Pregunta> preguntas = cuestionario.getPreguntas();

        List examenes = new ArrayList(preguntas);

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }
	
	@GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPreguntaId(preguntaId);
    }
	
	@DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPreguntaId(preguntaId);
    }

}
