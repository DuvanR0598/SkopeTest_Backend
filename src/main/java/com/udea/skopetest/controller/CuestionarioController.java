package com.udea.skopetest.controller;

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
import com.udea.skopetest.service.ICuestionarioService;

@RestController
@RequestMapping("/cuestionarios")
@CrossOrigin("*")
public class CuestionarioController {
	
	@Autowired
    private ICuestionarioService cuestionarioService;
	
	@PostMapping("/guardar")
    public ResponseEntity<Cuestionario> guardarCuestionario(@RequestBody Cuestionario cuestionario){
        return ResponseEntity.ok(cuestionarioService.agregarCuestionario(cuestionario));
    }
	
	@PutMapping("/")
    public ResponseEntity<Cuestionario> actualizarCuestionario(@RequestBody Cuestionario cuestionario){
        return ResponseEntity.ok(cuestionarioService.actualizarCuestionario(cuestionario));
    }
	
	@GetMapping("/")
    public ResponseEntity<?> listarCuestionarios(){
        return ResponseEntity.ok(cuestionarioService.obtenerCuestionarios());
    }
	
	@GetMapping("/{cuestionarioId}")
    public Cuestionario listarCuestionarioId(@PathVariable("cuestionarioId") Long cuestionarioId){
        return cuestionarioService.obtenerCuestionarioId(cuestionarioId);
    }
	
	@DeleteMapping("/{cuestionarioId}")
    public void eliminarExamen(@PathVariable("cuestionarioId") Long cuestionarioId){
		cuestionarioService.eliminarCuestionario(cuestionarioId);
    }

}
