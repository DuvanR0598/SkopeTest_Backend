package com.udea.skopetest.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.persistence.entity.UsuarioEntity;
import com.udea.skopetest.persistence.entity.UsuarioRolEntity;

public interface IUsuarioService {
	
	UsuarioEntity guardarUsuario(UsuarioEntity usuario, Set<UsuarioRolEntity> usuarioRoles);
	
	String eliminarUsuario(Long usuarioId);
	
	String actualizarUsuario(Usuario usuario);

	Map<String, Object> listarUsuarios(Pageable pageable);
	
	List<Usuario> listarUsuarios();
}