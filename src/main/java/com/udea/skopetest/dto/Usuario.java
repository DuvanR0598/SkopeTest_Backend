package com.udea.skopetest.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
	
	private Long identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private List<Rol> listaRoles;
	
}
