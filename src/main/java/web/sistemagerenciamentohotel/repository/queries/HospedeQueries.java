package web.sistemagerenciamentohotel.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.filter.HospedeFilter;

public interface HospedeQueries {
	Page<Hospede> pesquisar(HospedeFilter filtro, Pageable pageable);
}
