package web.sistemagerenciamentohotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.repository.queries.CheckInQueries;

public interface CheckInRepository extends JpaRepository <CheckIn, Long>, CheckInQueries{
	List<CheckIn> findByStatus(Status status);
	List<CheckIn> findByQuarto(Quarto quarto);
}
