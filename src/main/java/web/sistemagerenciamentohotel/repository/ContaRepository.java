package web.sistemagerenciamentohotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.repository.queries.ContaQueries;

public interface ContaRepository extends JpaRepository<Conta, Long>, ContaQueries {
	List<Conta> findByStatus(Status status);
}
