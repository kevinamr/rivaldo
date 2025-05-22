package br.sc.senac.dev.rivaldo_dev.service;

package br.sc.senac.dev.rivaldo_dev.service;

import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;


@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	public Pessoa inserir(Pessoa novaP) {
		validarCpfUsuarioNovo(novaP);
		
		
		
		return null;
	}
 
	private void validarCpfUsuarioNovo(Pessoa novaP) {
		
		String cpfNovo = novaP.getCpf();
		Pessoa cpfNovo = pessoaRepository.findByCpf(cpfNovo);
		
		if(cpfNovo != null) {
			throw new RivaldoException("jรก existe o cpf");
		}
		
	}

}