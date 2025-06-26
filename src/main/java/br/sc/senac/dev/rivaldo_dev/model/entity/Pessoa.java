package br.sc.senac.dev.rivaldo_dev.model.entity;


import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.sc.senac.dev.rivaldo_dev.enums.PeStatus;
import br.sc.senac.dev.rivaldo_dev.enums.PerfilAcesso;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Pessoa {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@NotBlank(message = "Nome é obrigatório")
@Size(min = 3, max = 255)
private String nome;

@NotBlank(message = "Cpf é obrigatório")
@Size(min = 11, max = 11)
@CPF(message = "Cpf é obrigatório")
private String cpf;

@NotBlank(message = "Email é obrigatório")
@Email(message = "Email precisa ser valido")
private String email;

@Column(length = 4000)
@NotBlank(message = "Senha é obrigatória")
private String senha;

@JsonBackReference(value ="solicitante")
@OneToMany(mappedBy = "solicitante")
private List<Chamado> chamadosPostados;

@Enumerated(EnumType.STRING)
private PerfilAcesso perfil;

@Enumerated(EnumType.STRING)
private PeStatus status;

}