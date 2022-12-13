package web.sistemagerenciamentohotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.repository.queries.HospedeQueries;

public interface HospedeRepository extends JpaRepository<Hospede, Long>, HospedeQueries{
	List<Hospede> findByNomeContainingIgnoreCase(String nome);
	List<Hospede> findByStatus(Status status);
}
