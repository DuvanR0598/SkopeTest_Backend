package com.udea.skopetest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Override
	public Map<String, Object> listarUsuarios(Pageable pageable) {
		Page<UsuarioEntity> pageUsuarioEnt = usuarioRepository.buscarUsuarioPaginado(pageable);
		List<UsuarioEntity> listaUsuarioEntity = pageUsuarioEnt.getContent();
		Map<String, Object> listaP = new HashMap<>();
		
		listaUsuarioEntity
		.stream()
		.map(this::entityToDto)
		.collect(Collectors.toList());
		
		listaP.put("listado", listaUsuarioEntity);
		listaP.put("paginaActual", pageUsuarioEnt.getNumber());
		listaP.put("totalElementos", pageUsuarioEnt.getTotalElements());
		listaP.put("totalPaginas", pageUsuarioEnt.getTotalPages());
		return listaP;
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		List<UsuarioEntity> listUsuarioEnt = usuarioRepository.findAll();
		List<Usuario> listUsuario = new ArrayList<>();
		UsuarioEntity usuarioEnt;
		Usuario usuario;
		
		for(int i=0; i<listUsuarioEnt.size(); i++) {
			usuarioEnt = listUsuarioEnt.get(i);
			usuario = entityToDto(usuarioEnt);
			listUsuario.add(usuario);
		}
		return listUsuario;
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
	
	private Usuario entityToDto(UsuarioEntity usuarioEnt) {
		Usuario usuario = new Usuario();
		usuario.setIdentificacion(usuarioEnt.getIdentificacion());
		usuario.setNombre(usuarioEnt.getNombre());
		usuario.setApellido(usuarioEnt.getApellido());
		usuario.setEmail(usuarioEnt.getEmail());
		usuario.setUsername(usuarioEnt.getUsername());
		return usuario;
	}
}
