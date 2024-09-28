package com.udea.skopetest.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.persistence.entity.RolEntity;
import com.udea.skopetest.persistence.entity.UsuarioEntity;
import com.udea.skopetest.persistence.entity.UsuarioRolEntity;
import com.udea.skopetest.persistence.repository.IUsuarioRepository;
import com.udea.skopetest.service.IUsuarioService;

@RestController
@CrossOrigin("*") //permite el intercambio de recursos(solicitudes) entre Back y Front
public class AuthController {
	
	@Autowired
	private IUsuarioRepository usuarioRepositorio;
	
	@Autowired
    private IUsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
    	
    	if(Boolean.TRUE.equals(usuarioRepositorio.existsByUsername(usuario.getUsername()))) {
			return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}else {
			Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();

	        RolEntity rol = new RolEntity();
	        rol.setRolId(usuario.getListaRoles().get(0).getRolId());
	        rol.setRolNombre(usuario.getListaRoles().get(0).getRolNombre());

	        UsuarioEntity usuarioEnt = guardarUsuario(usuario);
	        
	        UsuarioRolEntity usuarioRolEnt = new UsuarioRolEntity();
	        usuarioRolEnt.setUsuario(usuarioEnt);
	        usuarioRolEnt.setRol(rol);
	        
	        usuarioRoles.add(usuarioRolEnt);
	        usuarioService.guardarUsuario(usuarioEnt, usuarioRoles);
	        
	        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
		}
    }
	
	private UsuarioEntity guardarUsuario(Usuario usuario) {
		UsuarioEntity usuarioEnt = new UsuarioEntity();
		
		usuarioEnt.setIdentificacion(usuario.getIdentificacion());
		usuarioEnt.setNombre(usuario.getNombre());
		usuarioEnt.setApellido(usuario.getApellido());
		usuarioEnt.setEmail(usuario.getEmail());
		usuarioEnt.setUsername(usuario.getUsername());
		usuarioEnt.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioEnt;
	}

}
