package com.udea.skopetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
    private IUsuarioService usuarioService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("eliminar-usuario/{usuarioId}")
    public String eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
    	return usuarioService.eliminarUsuario(usuarioId);
    }
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("/actualizar-usuario")
	public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.actualizarUsuario(usuario));
	}

}
