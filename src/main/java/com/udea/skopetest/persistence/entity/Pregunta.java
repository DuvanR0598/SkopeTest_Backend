package com.udea.skopetest.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "preguntas")
public class Pregunta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;
	
	@Column(name = "texto")
	private String texto;
	
	@Column(name = "multimedia")
	private String multimedia;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cuestionario cuestionario;

}
