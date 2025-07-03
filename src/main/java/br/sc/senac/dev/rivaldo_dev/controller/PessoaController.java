package br.sc.senac.dev.rivaldo_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.dto.PessoaDTO;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/pessoa")
public class PessoaController {
 
@Autowired
private PessoaService pessoaService;


	@Operation(summary = "Inserir usuario", 
	   description = "força o banco a registrar um usuario sem regras de negocio")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pessoa inserir(@RequestBody Pessoa novaP) throws RivaldoException{
	return pessoaService.inserir(novaP);
	}
	
	@Operation(summary = "Registrar usuario", 
			   description = "Registra novos usuarios no banco")
    @PostMapping("/registrar")
    public ResponseEntity<Pessoa> registrar(@Valid @RequestBody PessoaDTO pessoaDTO) throws RivaldoException {
        return ResponseEntity.ok(pessoaService.registrar(pessoaDTO));
    }
	
	@Operation(summary = "Logar usuario", 
			   description = "Loga usuarios no banco")
	@PostMapping(path = "/login")
	public ResponseEntity<Pessoa> loginPessoa(@RequestBody Pessoa logandoPessoa) throws RivaldoException {
		return ResponseEntity.ok(pessoaService.loginPessoa(logandoPessoa));
	}
	
	@Operation(summary = "atualizar usuario", 
	   description = "atualiza usuario ja existentes no banco")
	@PutMapping
	public Pessoa atualizar(@RequestBody Pessoa novaPessoa) throws RivaldoException {
		Pessoa pessoaAtualizada = pessoaService.atualizar(novaPessoa);
	return pessoaService.atualizar(pessoaAtualizada);
	}
	
	
	@Operation(summary = "Pesquisar Todos os usuarios", 
	   description = "Retorna uma lista de todos os Usuarios cadastrados.")
	@GetMapping
	private List<Pessoa> pesquisarTodos(){
	List<Pessoa> tudo = pessoaService.pesquisarTodos();
	return tudo;
	};

	@Operation(summary = "Deletar pessoas por id", 
			description = "Deleta pessoas dado o seu id, desde q o usuario n tenha chamados em andamento")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@RequestBody Pessoa pessoaid) throws RivaldoException{
		pessoaService.excluirPessoa(pessoaid);
		return ResponseEntity.noContent().build();
}

	@Operation(summary = "Atualizar Status", 
			   description = "Atualiza os status de um usuario")
	@PostMapping(path = "/atualizar/status")
	public ResponseEntity<Object> atualizarStatus(@RequestBody Pessoa statusPessoa) throws RivaldoException{
		return ResponseEntity.ok(pessoaService.atualizarStatus(statusPessoa));
	}
}