package br.sc.senac.dev.rivaldo_dev.service;

import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;


@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	public Pessoa inserir(Pessoa novaP) throws RivaldoException {
		validarCpfUsuarioNovo(novaP);
		
		
		
		return null;
	}
 
	private void validarCpfUsuarioNovo(Pessoa novaP) throws RivaldoException{
		
		String cpfNovo = novaP.getCpf();
		Pessoa cpfNovoPessoa = pessoaRepository.findByCpf(cpfNovo);
		
		if(cpfNovoPessoa != null) {
		throw new RivaldoException("ja existe o cpf");
		}
		
	}

}