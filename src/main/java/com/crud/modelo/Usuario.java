package com.crud.modelo;


public class Usuario {
	
	protected int id;
	protected String nombre;
	protected String email;
	
	public Usuario() {}
	
	public Usuario(String nombre, String email) {
		
		super();
		this.nombre = nombre;
		this.email = email;
		
	}
	
	public Usuario(int id, String nombre, String email) {
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String name) {
		this.nombre = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
