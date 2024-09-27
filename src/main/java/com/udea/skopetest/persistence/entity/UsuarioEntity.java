package com.udea.skopetest.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class UsuarioEntity {
	
	@Id
	@Column(name = "cedula")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "apellido")
    private String apellido;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "telefono")
    private String telefono;
	
	@Column(name = "enable")
    private boolean enabled = true; 
	
	@Column(name = "perfil")
    private String perfil;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRolEntity> roles = new HashSet<>();

}
