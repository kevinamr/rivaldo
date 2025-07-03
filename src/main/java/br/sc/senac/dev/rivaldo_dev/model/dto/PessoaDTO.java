package br.sc.senac.dev.rivaldo_dev.model.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 255)
    private String nome;
    
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11)
    @CPF(message = "Cpf é obrigatório")
    private String cpf;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 30, message = "A senha deve ter entre 8 e 30 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).*$",
             message = "A senha deve conter pelo menos: uma letra maiúscula, uma minúscula, um número e um caractere especial")
    private String senha;
	
    
    
}
