package com.udea.skopetest.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udea.skopetest.dto.Login;
import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.persistence.entity.RolEntity;
import com.udea.skopetest.persistence.entity.UsuarioEntity;
import com.udea.skopetest.persistence.entity.UsuarioRolEntity;
import com.udea.skopetest.persistence.repository.IUsuarioRepository;
import com.udea.skopetest.security.CustomUserDetailsService;
import com.udea.skopetest.security.JWTAuthResponseDTO;
import com.udea.skopetest.security.JwtUtils;
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
	
	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
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
	
	@PostMapping("/iniciar-sesion")
    public ResponseEntity<?> generarToken(@RequestBody Login login) throws Exception {
        try{
            autenticar(login.getUsername(),login.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  userDetailsService.loadUserByUsername(login.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
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
