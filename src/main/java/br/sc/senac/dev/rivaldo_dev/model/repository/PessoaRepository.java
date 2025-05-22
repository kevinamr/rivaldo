package br.sc.senac.dev.rivaldo_dev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String>, JpaSpecificationExecutor<Pessoa> {
	
	
	@Query("SELECT ps FROM Pessoa ps where ps.cpf = : cpfNovo")
	Pessoa findByCpf(String cpfNovo);
	

}