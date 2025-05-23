package br.sc.senac.dev.rivaldo_dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.service.PessoaService;


@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa novaP) throws RivaldoException{
		return ResponseEntity.ok(pessoaService.inserir(novaP));
	}
	
	
	
	

}