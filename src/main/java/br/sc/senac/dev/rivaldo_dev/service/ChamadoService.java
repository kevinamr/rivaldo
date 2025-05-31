package br.sc.senac.dev.rivaldo_dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.enums.Categoria;
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
		if(novoChamado.getCategoria() == null) {
			throw new RivaldoException("Usuario n categoria");
		}
		
		return chamadoRepository.save(novoChamado);
	}

	public List<Chamado> procurarTodos() {
		return chamadoRepository.findAll();
	}

	public List<Chamado> procurarPorCategoria(Integer codigoCategoria) {
		
		String categoria = processarCategoria(codigoCategoria);
	    
		
		return chamadoRepository.findByCategoria(categoria);
	}
	
	private String processarCategoria(Integer codigoCategoria) {
        Categoria categoria = Categoria.fromInt(codigoCategoria);
        return categoria.name();
	}

	public void excluir(String id) {
		
		chamadoRepository.deleteById(id);
	}
	
}
