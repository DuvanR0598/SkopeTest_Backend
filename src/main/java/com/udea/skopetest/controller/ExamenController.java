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

import com.udea.skopetest.persistence.entity.Examen;
import com.udea.skopetest.service.IExamenService;

@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamenController {
	
	@Autowired
    private IExamenService examenService;
	
	@PostMapping("/guardar")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
		Examen examenGuardada = examenService.agregarExamen(examen);
        return ResponseEntity.ok(examenGuardada);
    }
	
	@GetMapping("/{examenId}")
    public Examen listarExamenPorId(@PathVariable("examenId") Long examenId){
        return examenService.obtenerExamenId(examenId);
    }
	
	@GetMapping("/listar")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }
	
	@PutMapping("/actualizar")
    public Examen actualizarExamen(@RequestBody Examen examen){
        return examenService.actualizarExamen(examen);
    }
	
	@DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId){
		examenService.eliminarExamen(examenId);
    }

}
