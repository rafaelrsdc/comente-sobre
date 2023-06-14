package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String comentario;
    private String topico;

    // Construtores, getters e setters

    public Comentario() {
    }

    public Comentario(String email, String comentario, String topico) {
        this.email = email;
        this.comentario = comentario;
        this.topico = topico;
    }

    // Getters e setters omitidos para brevidade

    public void setTopico(String topico) {
        this.topico = topico;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTopico() {
		return topico;
	}
}
