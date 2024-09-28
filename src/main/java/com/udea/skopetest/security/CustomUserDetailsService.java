package com.udea.skopetest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udea.skopetest.persistence.entity.UsuarioEntity;
import com.udea.skopetest.persistence.repository.IUsuarioRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private IUsuarioRepository usuarioRepository;

    //Esta clase lo hace es cargar un usuario y/o buscar un usuario por el username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEnt = usuarioRepository.findByUsername(username)
        		.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el username : " + username));
          
//        return new User(usuarioEnt.getUsername(), usuarioEnt.getPassword(), mapearRoles(usuarioEnt.getRoles()));
        return usuarioEnt;
    }

//    private Collection<? extends GrantedAuthority> mapearRoles(Set<UsuarioRolEntity> roles){
//		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getRol().getNombre())).collect(Collectors.toList());
//	}

}
