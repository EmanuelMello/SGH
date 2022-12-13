package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.filter.QuartoFilter;

public interface QuartoQueries {

	Page<Quarto> pesquisar(QuartoFilter filtro, Pageable pageable, boolean ehParaAplicacao);
	
}