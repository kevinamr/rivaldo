package br.sc.senac.dev.rivaldo_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/api/pessoa")
public class PessoaController {

@Autowired
private PessoaService pessoaService;


@Operation(summary = "Registrar usuario", 
   description = "Registra novos usuarios no banco")
@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public Pessoa inserir(@RequestBody Pessoa novaP) throws RivaldoException{
return pessoaService.inserir(novaP);
}

@Operation(summary = "atualizar usuario", 
   description = "atualiza usuario ja existentes no banco")
@PutMapping
public Pessoa atualizar(@RequestBody Pessoa novaPessoa) throws RivaldoException {
return pessoaService.atualizar(novaPessoa);
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
public ResponseEntity<Void> excluir(@PathVariable String id) throws RivaldoException{
pessoaService.excluir(id);
return ResponseEntity.noContent().build();
}


}