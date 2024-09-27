package com.udea.skopetest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
	
	private Long id;
	private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private boolean enabled = true; 
    private String perfil;
	
}
