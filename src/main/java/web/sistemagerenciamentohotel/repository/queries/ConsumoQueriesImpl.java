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

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.ConsumoFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class ConsumoQueriesImpl implements ConsumoQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Consumo> pesquisar(ConsumoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Consumo> criteriaQuery = builder.createQuery(Consumo.class);
		Root<Consumo> c = criteriaQuery.from(Consumo.class);
		TypedQuery<Consumo> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(c.<Long>get("codigo"), filtro.getCodigo()));
		}
		
		if (StringUtils.hasText(filtro.getDescricao())) {
			predicateList.add(builder.like(builder.lower(c.<String>get("descricao")),
					"%" + filtro.getDescricao().toLowerCase() + "%"));
		}

		if (filtro.getValorUnitario() != null) {
			predicateList.add(builder.equal(c.<BigDecimal>get("valor"), filtro.getValorUnitario()));
		}

		if (filtro.getQuantidade() != null) {
			predicateList.add(builder.equal(c.<Integer>get("quantidade"), filtro.getQuantidade()));
		}

		predicateList.add(builder.equal(c.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(c).where(predArray);

		PaginacaoUtil.prepararOrdem(c, criteriaQuery, builder, pageable);

		typedQuery = manager.createQuery(criteriaQuery);

		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Consumo> consumos = typedQuery.getResultList();

		long totalConsumos = PaginacaoUtil.getTotalRegistros(c, predArray, builder, manager);
		Page<Consumo> page = new PageImpl<>(consumos, pageable, totalConsumos);

		return page;
	}

}
