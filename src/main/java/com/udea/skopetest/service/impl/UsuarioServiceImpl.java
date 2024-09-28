package com.udea.skopetest.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
