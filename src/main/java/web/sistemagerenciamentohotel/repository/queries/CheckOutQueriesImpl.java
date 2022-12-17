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

import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.CheckOutFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class CheckOutQueriesImpl implements CheckOutQueries {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<CheckOut> pesquisar(CheckOutFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CheckOut> criteriaQuery = builder.createQuery(CheckOut.class);
		Root<CheckOut> c = criteriaQuery.from(CheckOut.class);
		TypedQuery<CheckOut> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(c.<Long>get("codigo"), filtro.getCodigo()));
		}
		if (filtro.getValorTotal() != null) {
			predicateList.add(builder.equal(c.<BigDecimal>get("valor"), filtro.getValorTotal()));
		}

		predicateList.add(builder.equal(c.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(c).where(predArray);

		PaginacaoUtil.prepararOrdem(c, criteriaQuery, builder, pageable);

		typedQuery = manager.createQuery(criteriaQuery);

		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<CheckOut> checkOuts = typedQuery.getResultList();

		long totalCheckOuts = PaginacaoUtil.getTotalRegistros(c, predArray, builder, manager);
		Page<CheckOut> page = new PageImpl<>(checkOuts, pageable, totalCheckOuts);

		return page;
	}
}
