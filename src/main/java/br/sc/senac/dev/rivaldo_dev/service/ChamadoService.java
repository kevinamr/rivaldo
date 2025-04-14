package br.sc.senac.dev.rivaldo_dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.model.repository.ChamadoRepository;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public Chamado publicar(Chamado novoChamado) {
		return chamadoRepository.save(novoChamado);
	}
	
}
