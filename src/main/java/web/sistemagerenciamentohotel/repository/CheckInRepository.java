package web.sistemagerenciamentohotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.CheckIn;

public interface CheckInRepository extends JpaRepository <CheckIn, Long>{
	
}
