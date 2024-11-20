package com.udea.skopetest.persistence.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Table(name = "cuestionario")
@Entity
@Getter
@Setter
public class Cuestionario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuestionario;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Examen examen;

    @OneToMany(mappedBy = "cuestionario",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();


}
