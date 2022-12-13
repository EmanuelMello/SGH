package web.sistemagerenciamentohotel.repository.queries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.ContaFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class ContaQueriesImpl implements ContaQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Conta> pesquisar(ContaFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Conta> criteriaQuery = 	builder.createQuery(Conta.class);
		Root<Conta> c = criteriaQuery.from(Conta.class);
		c.fetch("consumo",JoinType.INNER);
		TypedQuery<Conta> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(c.<Long>get("codigo"), filtro.getCodigo()));
		}
		
		if (filtro.getCodigoConsumo() != null) {
			predicateList.add(builder.equal(c.<Consumo>get("consumo").<Long>get("codigo"),filtro.getCodigoConsumo()));
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
		
		List<Conta> consumos = typedQuery.getResultList();
		
		long totalContas = PaginacaoUtil.getTotalRegistros(c, predArray, builder, manager);
		Page<Conta> page = new PageImpl<>(consumos, pageable, totalContas); 
		
		return page;
	}

}
