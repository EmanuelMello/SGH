package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.model.filter.CheckOutFilter;

public interface CheckOutQueries {
	Page<CheckOut> pesquisar(CheckOutFilter filtro, Pageable pageable);
}
