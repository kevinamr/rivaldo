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
import br.sc.senac.dev.rivaldo_dev.model.dto.PessoaDTO;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;
import br.sc.senac.dev.rivaldo_dev.model.repository.ChamadoRepository;
import br.sc.senac.dev.rivaldo_dev.model.repository.PessoaRepository;
import jakarta.validation.Valid;


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
	novaP.setStatus(PeStatus.ATIVADO);
	
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

if(pessoaExistente == null) {
throw new RivaldoException("informe um id valido!");
}

if(pessoaAtualizado.getNome() != null) {
	pessoaExistente.setNome(pessoaAtualizado.getNome());
}

if(pessoaAtualizado.getEmail() != null) {
	pessoaExistente.setEmail(pessoaAtualizado.getEmail());
}

if(pessoaAtualizado.getPerfil() != null) {
	pessoaExistente.setPerfil(pessoaAtualizado.getPerfil());
}

if(pessoaAtualizado.getSenha() != null) {
    String senhaNova = pessoaAtualizado.getSenha();
    
    // Verifica se já está hasheada (SHA-256 = 64 caracteres hex)
    if (senhaNova.length() != 64 || !senhaNova.matches("[0-9a-f]+")) {
        // Só faz hash se NÃO estiver hasheada
        String senhaHash = DarHashNaSenha(senhaNova);
        pessoaExistente.setSenha(senhaHash);
    } else {
        // Já está hasheada, usa direto
        pessoaExistente.setSenha(senhaNova);
    }
}


pessoaRepository.save(pessoaExistente);

return pessoaExistente;
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

public Pessoa registrar(PessoaDTO pessoaDTO) throws RivaldoException {
    // Converter DTO para Entity
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(pessoaDTO.getNome());
    pessoa.setCpf(pessoaDTO.getCpf());
    pessoa.setEmail(pessoaDTO.getEmail());
    
    pessoa.setPerfil(PerfilAcesso.USUARIO);
    pessoa.setStatus(PeStatus.ATIVADO);
    
    // Hash da senha APÓS validação
    String senhaHash = DarHashNaSenha(pessoaDTO.getSenha());
    pessoa.setSenha(senhaHash);
    
    validarUsuarioNovo(pessoa);
    
    return pessoaRepository.save(pessoa);
}

}