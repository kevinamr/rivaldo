package br.sc.senac.dev.rivaldo_dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;


@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa inserir(Pessoa novaP) throws RivaldoException {
		validarCpfUsuarioNovo(novaP);
		return pessoaRepository.save(novaP);
	}
 
	private void validarCpfUsuarioNovo(Pessoa novaP) throws RivaldoException{
		
		String cpfNovo = novaP.getCpf();
		Pessoa cpfNovoPessoa = pessoaRepository.findByCpf(cpfNovo);
		
		if(cpfNovoPessoa != null) {
	    	throw new RivaldoException("ja existe o cpf");
		}
		
	}

	public List<Pessoa> pesquisarTodos() {
		return pessoaRepository.findAll();
	}

	public void excluir(String id) {
		pessoaRepository.deleteById(id);
		
	}

}