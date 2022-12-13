package web.sistemagerenciamentohotel.repository.queries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.QuartoFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class QuartoQueriesImpl implements QuartoQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Quarto> pesquisar(QuartoFilter filtro, Pageable pageable,boolean ehParaAplicacao) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Quarto> criteriaQuery = builder.createQuery(Quarto.class);
		Root<Quarto> q = criteriaQuery.from(Quarto.class);
		TypedQuery<Quarto> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(q.<Long>get("codigo"), filtro.getCodigo()));
		}
		if (StringUtils.hasText(filtro.getDescricao())) {
			predicateList.add(builder.like(builder.lower(q.<String>get("descricao")),
					"%" + filtro.getDescricao().toLowerCase() + "%"));
		}
		if (StringUtils.hasText(filtro.getNumero())) {
			predicateList.add(
					builder.like(builder.lower(q.<String>get("numero")), "%" + filtro.getNumero().toLowerCase() + "%"));
		}
		
		if (filtro.getValor() != null) {
			predicateList.add(builder.equal(q.<BigDecimal>get("valor"), filtro.getValor()));
		}

		predicateList.add(builder.equal(q.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(q).where(predArray);

		PaginacaoUtil.prepararOrdem(q, criteriaQuery, builder, pageable);

		typedQuery = manager.createQuery(criteriaQuery);

		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Quarto> quartos = typedQuery.getResultList();

		long totalQuartos = PaginacaoUtil.getTotalRegistros(q, predArray, builder, manager);
		Page<Quarto> page = new PageImpl<>(quartos, pageable, totalQuartos);

		return page;
	}

}
