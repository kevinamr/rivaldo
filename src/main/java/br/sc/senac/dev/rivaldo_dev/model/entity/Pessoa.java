package br.sc.senac.dev.rivaldo_dev.model.entity;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Pessoa {

	@Id
	private Integer id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String email;

	@NotNull
	private String senha;
	
}
