package com.udea.skopetest.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.persistence.entity.UsuarioEntity;
import com.udea.skopetest.persistence.entity.UsuarioRolEntity;
import com.udea.skopetest.persistence.repository.IRolRepository;
import com.udea.skopetest.persistence.repository.IUsuarioRepository;
import com.udea.skopetest.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
    private IUsuarioRepository usuarioRepository;
	
	@Autowired
    private IRolRepository rolRepository;

	@Override
	public UsuarioEntity guardarUsuario(UsuarioEntity usuario, Set<UsuarioRolEntity> usuarioRoles) {
		
		for(UsuarioRolEntity usuarioRol:usuarioRoles){
            rolRepository.save(usuarioRol.getRol());
            
        }
        usuario.getRoles().addAll(usuarioRoles);
        return usuarioRepository.save(usuario);
	}
	
	@Override
    public String eliminarUsuario(Long usuarioId) {
    	if(usuarioRepository.findById(usuarioId).isPresent()) {
    		usuarioRepository.deleteById(usuarioId);
    		return "Usuario eliminado correctamente!";
    	}
    	return "Error! el usuario no existe";
    }
	
	@Override
	public String actualizarUsuario(Usuario usuario) {
		Optional<UsuarioEntity> usuarioEntOpt = usuarioRepository.findById(usuario.getIdentificacion());
		if(usuarioEntOpt.isPresent()) {
			UsuarioEntity usuarioEnt1 = usuarioEntOpt.get();
			UsuarioEntity usuarioEnt = dtoToEntity(usuario);
			usuarioEnt.setPassword(usuarioEnt1.getPassword());
			
			usuarioRepository.save(usuarioEnt);
			return "Usuario actualizado con exito";
		}
		return "El usuario no existe en el sistema";
	}
	
	
	private UsuarioEntity dtoToEntity(Usuario usuario) {
		UsuarioEntity usuarioEnt = new UsuarioEntity();
		usuarioEnt.setIdentificacion(usuario.getIdentificacion());
		usuarioEnt.setNombre(usuario.getNombre());
		usuarioEnt.setApellido(usuario.getApellido());
		usuarioEnt.setEmail(usuario.getEmail());
		usuarioEnt.setUsername(usuario.getUsername());
		return usuarioEnt;
	}
}
