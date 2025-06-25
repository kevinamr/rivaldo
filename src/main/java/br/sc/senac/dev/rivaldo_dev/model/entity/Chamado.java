package br.sc.senac.dev.rivaldo_dev.model.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.sc.senac.dev.rivaldo_dev.enums.Categoria;
import br.sc.senac.dev.rivaldo_dev.enums.OsStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "A Descrição é obrigatória")
	@Size(min = 3, max = 255)
	private String descricao;
	
    @Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa solicitante;
	
	@Enumerated(EnumType.STRING)
	private OsStatus status;
	
	private boolean stats;
	
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
}
  