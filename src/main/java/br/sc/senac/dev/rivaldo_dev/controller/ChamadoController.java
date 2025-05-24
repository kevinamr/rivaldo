package br.sc.senac.dev.rivaldo_dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
			   description = "Publicar um chamado novo")
	@PostMapping
	public Chamado publicar(@RequestBody Chamado novoChamado) throws RivaldoException {
		return chamadoService.publicar(novoChamado);
	}

}
