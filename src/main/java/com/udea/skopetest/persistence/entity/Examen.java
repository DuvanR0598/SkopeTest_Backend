package com.udea.skopetest.persistence.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Table(name = "examen")
@Entity
@Getter
@Setter
public class Examen {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamen;
	
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	
	@Column(name = "duracion")
	private int duracion;
	
	@Column(name = "estado")
	private boolean estado;
	
	@Column(name = "practica")
	private boolean practica;
	
	@OneToMany(mappedBy = "examen",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cuestionario> cuestionario = new LinkedHashSet<>();

}
