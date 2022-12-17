package web.sistemagerenciamentohotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.repository.queries.CheckOutQueries;

public interface CheckOutRepository extends JpaRepository<CheckOut, Long>, CheckOutQueries{

}
