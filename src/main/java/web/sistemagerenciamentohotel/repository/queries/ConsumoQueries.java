package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.model.filter.ConsumoFilter;

public interface ConsumoQueries {

	Page<Consumo> pesquisar(ConsumoFilter filtro, Pageable pageable);
	
}