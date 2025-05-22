package br.sc.senac.dev.rivaldo_dev.model.entity;

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
	
	private String nome;
	
	private String cpf;
	
	private String email;

	private String senha;
	
}
