package br.sc.senac.dev.rivaldo_dev.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.sc.senac.dev.rivaldo_dev.enums.Categoria;
import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;
import br.sc.senac.dev.rivaldo_dev.model.entity.Pessoa;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer>, JpaSpecificationExecutor<Chamado> {

    @Query("SELECT ch FROM Chamado ch WHERE ch.status = 'ANDAMENTO'")
	List<Chamado> findByStatus(); 

	@Query("SELECT ch FROM Chamado ch WHERE ch.categoria = :categoriaSelecionada")
	List<Chamado> findByCategoria(@Param("categoriaSelecionada")Categoria categoriaSelecionada);
	
    @Query("SELECT COUNT(ch) FROM Chamado ch WHERE ch.solicitante.id = :idUsuario")
	long countByIdUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT ch FROM Chamado ch WHERE ch.stats = :stats")
	List<Chamado> findByStats(@Param("stats") boolean stats); 
    
	@Query("SELECT ch FROM Chamado ch WHERE ch.id = :idChamadoSt")
	Chamado findById(@Param("idChamadoSt") int idChamadoSt);
	
}