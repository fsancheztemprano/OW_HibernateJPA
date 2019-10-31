package com.openwebinars.hibernate.envers;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

/**
 * Entity implementation class for Entity: Categoria
 *
 */
@Entity
@Audited
public class Categoria implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nombre;
	private static final long serialVersionUID = 1L;

	public Categoria() {
		super();
	}   
	
	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	
	public long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}
