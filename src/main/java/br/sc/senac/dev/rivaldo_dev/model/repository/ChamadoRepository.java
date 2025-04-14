package br.sc.senac.dev.rivaldo_dev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.sc.senac.dev.rivaldo_dev.model.entity.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, String>, JpaSpecificationExecutor<Chamado> {
	
	
}