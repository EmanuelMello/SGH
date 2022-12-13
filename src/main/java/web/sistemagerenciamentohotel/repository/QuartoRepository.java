package web.sistemagerenciamentohotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.StatusQuarto;
import web.sistemagerenciamentohotel.repository.queries.QuartoQueries;

public interface QuartoRepository extends JpaRepository<Quarto, Long>, QuartoQueries {

	List<Quarto> findByNumeroContainingIgnoreCase(String numero);
	List<Quarto> findByStatusQuarto(StatusQuarto statusQuarto);
}