package br.sc.senac.dev.rivaldo_dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.enums.OsStatus;
import br.sc.senac.dev.rivaldo_dev.enums.PerfilAcesso;
import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.dto.PessoaDTO;
import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.ChamadoRepository;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;


@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Chamado publicar(Chamado novoChamado) throws RivaldoException {
		novoChamado.setStatus(OsStatus.TRIAGEM);
		return chamadoRepository.save(novoChamado);
	}

	public List<Chamado> procurarChamadosEmAndamento(String email) {
		//String emailUsado = emailUsuario.getEmail();
		Pessoa usuarioLogado = pessoaRepository.findByEmail(email);
		
		if (usuarioLogado == null) {
		    throw new RuntimeException("Usuário não encontrado para o e-mail: " + email);
		}
		
		List<Chamado> chamadosAchados = null;
		
		if(usuarioLogado.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
			chamadosAchados = chamadoRepository.findByStatus();
		} else {
			Integer idLogado = usuarioLogado.getId();
			chamadosAchados = chamadoRepository.findByStatusAndSolicitanteEmail(email);
		}
		return chamadosAchados;
	}

	public void excluir(Chamado chamadoid) {
		
		int id = chamadoid.getId();
		
		chamadoRepository.deleteById(id);
	}

	public Object atualizarStatus(Chamado statusChamado) throws RivaldoException {
	    if(statusChamado.getId() == null) {
	        throw new RivaldoException("id n informado");
	    }
	    
	    int idChamadoSt = statusChamado.getId();
	    
	    Chamado chamadoNoBanco = chamadoRepository.findById(idChamadoSt);
	    
	    if(chamadoNoBanco == null) {
	    	throw new RivaldoException("pessoa n encontrada");
	    }
	    
	    if(chamadoNoBanco.getStatus() == OsStatus.TRIAGEM) {
	        chamadoNoBanco.setStatus(OsStatus.ANDAMENTO);
	    } else if(chamadoNoBanco.getStatus() == OsStatus.ANDAMENTO) {
	        chamadoNoBanco.setStatus(OsStatus.CONCLUIDO);
	    } else {
	        chamadoNoBanco.setStatus(OsStatus.ANDAMENTO);
	    }
	    
	    chamadoRepository.save(chamadoNoBanco);
		
		return chamadoNoBanco;
	}

	public Object cancelarStatus(Chamado statusChamado) throws RivaldoException {
	    if(statusChamado.getId() == null) {
	        throw new RivaldoException("id n informado");
	    }
	    
	    int idChamadoSt = statusChamado.getId();
	    
	    Chamado chamadoNoBanco = chamadoRepository.findById(idChamadoSt);
	    
	    if(chamadoNoBanco == null) {
	    	throw new RivaldoException("pessoa n encontrada");
	    }
	    
	    chamadoNoBanco.setStatus(OsStatus.CANCELADO);
	    
	    chamadoRepository.save(chamadoNoBanco);
	    
		return chamadoNoBanco;
	}
	
}
