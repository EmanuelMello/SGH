package web.sistemagerenciamentohotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.repository.queries.ConsumoQueries;

public interface ConsumoRepository extends JpaRepository<Consumo, Long>, ConsumoQueries {

	List<Consumo> findByDescricaoContainingIgnoreCase(String descricao);
	List<Consumo> findByStatus(Status status);
}