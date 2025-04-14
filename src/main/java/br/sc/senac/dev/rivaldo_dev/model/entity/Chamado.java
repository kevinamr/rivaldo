package br.sc.senac.dev.rivaldo_dev.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Chamado {
	
	@Id 
	private Integer id;
	
	private String descricao;
	
	//private Categoria categoria;
	
	//@ManyToOne
	//@JoinColumn(name = "id_usuario")
	//private Usuario solicitante;

}
