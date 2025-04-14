package br.sc.senac.dev.rivaldo_dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.service.ChamadoService;


@RestController
public class ChamadoController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	public Chamado publicar(@RequestBody Chamado novoChamado) {
		return chamadoService.publicar(novoChamado);
	}

}
