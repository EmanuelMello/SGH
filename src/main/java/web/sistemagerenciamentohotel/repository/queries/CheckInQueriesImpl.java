package web.sistemagerenciamentohotel.repository.queries;

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

import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.CheckInFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class CheckInQueriesImpl implements CheckInQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<CheckIn> pesquisar(CheckInFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CheckIn> criteriaQuery = 	builder.createQuery(CheckIn.class);
		Root<CheckIn> c = criteriaQuery.from(CheckIn.class);
		c.fetch("hospede",JoinType.INNER);
		TypedQuery<CheckIn> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(c.<Long>get("codigo"), filtro.getCodigo()));
		}
		
		if (filtro.getCodigoHospede() != null) {
			predicateList.add(builder.equal(c.<Hospede>get("hospede").<Long>get("codigo"),filtro.getCodigoHospede()));
		}
		
		if (filtro.getCodigoQuarto() != null) {
			predicateList.add(builder.equal(c.<Quarto>get("quarto").<Long>get("codigo"),filtro.getCodigoQuarto()));
		}
		
		predicateList.add(builder.equal(c.<Status>get("status"), Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(c).where(predArray);
		
		PaginacaoUtil.prepararOrdem(c, criteriaQuery, builder, pageable);
		
		typedQuery = manager.createQuery(criteriaQuery);
						
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
		
		List<CheckIn> hospedes = typedQuery.getResultList();
		
		long totalCheckIns = PaginacaoUtil.getTotalRegistros(c, predArray, builder, manager);
		Page<CheckIn> page = new PageImpl<>(hospedes, pageable, totalCheckIns); 
		
		return page;
	}

}
