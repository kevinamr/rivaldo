package br.sc.senac.dev.rivaldo_dev.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.sc.senac.dev.rivaldo_dev.enums.PeStatus;
import br.sc.senac.dev.rivaldo_dev.enums.PerfilAcesso;
import br.sc.senac.dev.rivaldo_dev.exception.RivaldoException;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.ChamadoRepository;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;


@Service
public class PessoaService {

@Autowired
private PessoaRepository pessoaRepository;

@Autowired
private ChamadoRepository chamadoRepository;

public Pessoa inserir(Pessoa novaP) throws RivaldoException {
validarUsuarioNovo(novaP);
String senhaHash = DarHashNaSenha(novaP.getSenha());
novaP.setSenha(senhaHash);
if(novaP.getPerfil() == null) {
	novaP.setPerfil(PerfilAcesso.USUARIO);
}
if(novaP.getStatus() == null) {
	novaP.setStatus(PeStatus.ATIVADO);
}
return pessoaRepository.save(novaP);
}

private String DarHashNaSenha(String senha) throws RivaldoException {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
             if (hex.length() == 1) hexString.append('0');
             hexString.append(hex);
                    }
                    return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RivaldoException("Erro ao gerar hash SHA-256");
    }
}

private void validarUsuarioNovo(Pessoa novaP) throws RivaldoException{

String cpfNovo = novaP.getCpf();
Pessoa cpfNovoPessoa = pessoaRepository.findByCpf(cpfNovo);

if(cpfNovoPessoa != null) {
    throw new RivaldoException("ja existe o cpf");
}

}

public List<Pessoa> pesquisarTodos() {
return pessoaRepository.findAll();
}

public void excluirPessoa(Pessoa pessoaid) throws RivaldoException {
	
	int id = pessoaid.getId();
	
	long totalDeChamadosCriados = chamadoRepository.countByIdUsuario(id);
	
	if(totalDeChamadosCriados > 0 ) {
		throw new RivaldoException("Usuario #" + id + "  já possui Chamados postados, logo não pode ser excluído.");
	}
	
	pessoaRepository.deleteById(id);
}

public Pessoa atualizar(Pessoa pessoaAtualizado) throws RivaldoException {

int idPessoaNova = pessoaAtualizado.getId();
Pessoa pessoaExistente = pessoaRepository.findById(idPessoaNova);

if(pessoaExistente == null || pessoaAtualizado.getId() == null) {
throw new RivaldoException("informe um id valido!");
}

return pessoaRepository.save(pessoaAtualizado);
}

public Pessoa loginPessoa(Pessoa logandoPessoa) throws RivaldoException {
	
    if(logandoPessoa.getEmail() == null) {
        throw new RivaldoException("email n informado");
    }
    if(logandoPessoa.getSenha() == null) {
        throw new RivaldoException("senha n informado");
    }

    Pessoa pessoaLogado = pessoaRepository.findByEmail(logandoPessoa.getEmail());
    
   
    if(pessoaLogado == null) {
        throw new RivaldoException("Email não encontrado");
    }

    String senhaHash = DarHashNaSenha(logandoPessoa.getSenha());
    
    if(pessoaLogado.getStatus() == PeStatus.DESATIVADO) {
    	throw new RivaldoException("usuario desativado");
    }
    
    if(!senhaHash.equals(pessoaLogado.getSenha())) {
        throw new RivaldoException("senha incorreta");
    }
    
    return pessoaLogado;
}

public Object atualizarStatus(Pessoa statusPessoa) throws RivaldoException {
	
    if(statusPessoa.getId() == null) {
        throw new RivaldoException("id n informado");
    }
    
    int idPessoaSt = statusPessoa.getId();
    
    Pessoa pessoaLogado = pessoaRepository.findById(idPessoaSt);
    
    if(pessoaLogado == null) {
    	throw new RivaldoException("pessoa n encontrada");
    }
    
    if(pessoaLogado.getStatus() == PeStatus.ATIVADO) {
        pessoaLogado.setStatus(PeStatus.DESATIVADO);
    } else {
        pessoaLogado.setStatus(PeStatus.ATIVADO);
    }
    
    pessoaRepository.save(pessoaLogado);
	
	return pessoaLogado;
}

}