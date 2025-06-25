package br.sc.senac.dev.rivaldo_dev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa> {
	
	@Query("SELECT p FROM Pessoa p WHERE p.cpf = :cpfNovo")
	Pessoa findByCpf(@Param("cpfNovo") String cpfNovo);

	@Query("SELECT p FROM Pessoa p WHERE p.id = :idPessoaNova")
	Pessoa findById(@Param("idPessoaNova") int idPessoaNova);
	
	@Query("SELECT p FROM Pessoa p WHERE p.email = :email")
	Pessoa findByEmail(@Param("email") String email);
}