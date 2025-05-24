package br.sc.senac.dev.rivaldo_dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.model.repository.ChamadoRepository;


@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public Chamado publicar(Chamado novoChamado) throws RivaldoException {
		if(novoChamado.getDescricao() == null) {
			throw new RivaldoException("Texto n informado");
		}
		//if(novoChamado.get() == null) {
		//	throw new RivaldoException("Usuario n informado");
		//}
		
		return chamadoRepository.save(novoChamado);
	}
	
}
