package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.filter.CheckInFilter;

public interface CheckInQueries {

	Page<CheckIn> pesquisar(CheckInFilter filtro, Pageable pageable);
	
}
