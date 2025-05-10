package br.sc.senac.dev.rivaldo_dev.model.entity;

import org.antlr.v4.runtime.misc.NotNull;

import br.sc.senac.dev.rivaldo_dev.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
    @Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	//@ManyToOne
	//@JoinColumn(name = "id_usuario")
    
	//private Usuario solicitante;

}
