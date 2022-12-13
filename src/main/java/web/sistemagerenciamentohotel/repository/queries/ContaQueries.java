package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.model.filter.ContaFilter;

public interface ContaQueries {

	Page<Conta> pesquisar(ContaFilter filtro, Pageable pageable);
	
}
