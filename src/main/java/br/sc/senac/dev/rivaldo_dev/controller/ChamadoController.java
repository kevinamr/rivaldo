package br.sc.senac.dev.rivaldo_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/api/chamado")
public class ChamadoController {

@Autowired
private ChamadoService chamadoService;

@Operation(summary = "Publicar um chamado novo", 
   description = "Publica um chamado novo")
@PostMapping
public Chamado publicar(@RequestBody Chamado novoChamado) throws RivaldoException {
return chamadoService.publicar(novoChamado);
}

@Operation(summary = "Pesquisar todos os Chamados", 
   description = "Busca todos os chamados disponiveis")
@GetMapping
private List<Chamado> procurarChamadosEmAndamento(){
List<Chamado> tudo = chamadoService.procurarChamadosEmAndamento();
return tudo;
}


@Operation(summary = "Deletar chamados por id",
   description = "Deleta chamados dado o seu id")
@DeleteMapping("/{id}")
public ResponseEntity<Void> excluir(@RequestBody Chamado chamadoid) throws RivaldoException{
chamadoService.excluir(chamadoid);
return ResponseEntity.noContent().build();
}

@Operation(summary = "Atualizar Status", 
description = "Atualiza os status de um chamado")
@PostMapping(path = "/atualizar/status")
public ResponseEntity<Object> atualizarStatus(@RequestBody Chamado statusChamado) throws RivaldoException{
	return ResponseEntity.ok(chamadoService.atualizarStatus(statusChamado));
}

@Operation(summary = "Cancelar Status", 
description = "Cancela um chamado, mudando seu status")
@PostMapping(path = "/cancelar/status")
public ResponseEntity<Object> cancelarStatus(@RequestBody Chamado statusChamado) throws RivaldoException{
	return ResponseEntity.ok(chamadoService.cancelarStatus(statusChamado));
}

}
