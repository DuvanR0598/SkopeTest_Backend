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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class RolEntity {
	
	@Id
	@Column(name = "id_rol")
    private Long rolId;
	
	@Column(name = "nombre")
    private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();

}
