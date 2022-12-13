package web.sistemagerenciamentohotel.repository.queries;
//package web.controlevacinacao.repository.queries;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.JoinType;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import web.controlevacinacao.model.Lote;
//import web.controlevacinacao.model.Status;
//import web.controlevacinacao.model.Quarto;
//import web.controlevacinacao.model.filter.LoteFilter;
//import web.controlevacinacao.repository.pagination.PaginacaoUtil;
//
//public class LoteQueriesImpl implements LoteQueries {
//
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public Page<Lote> pesquisar(LoteFilter filtro, Pageable pageable) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Lote> criteriaQuery = builder.createQuery(Lote.class);
//		Root<Lote> l = criteriaQuery.from(Lote.class);
//		l.fetch("vacina", JoinType.INNER);
//		TypedQuery<Lote> typedQuery;
//		List<Predicate> predicateList = new ArrayList<>();
//
//		if (filtro.getCodigo() != null) {
//			predicateList.add(builder.equal(l.<Long>get("codigo"), filtro.getCodigo()));
//		}
//		
//		if (filtro.getValidadeInicial() != null) {
//			predicateList.add(builder.greaterThanOrEqualTo(l.<LocalDate>get("validade"), 
//					filtro.getValidadeInicial()));
//		}
//		if (filtro.getValidadeFinal() != null) {
//			predicateList.add(builder.lessThanOrEqualTo(l.<LocalDate>get("validade"), 
//					filtro.getValidadeFinal()));
//		}
//		
//		if (filtro.getNroDosesDoLoteInicial() != null) {
//			predicateList.add(builder.greaterThanOrEqualTo(l.<Integer>get("nroDosesDoLote"), 
//					filtro.getNroDosesDoLoteInicial()));
//		}
//		if (filtro.getNroDosesDoLoteFinal() != null) {
//			predicateList.add(builder.lessThanOrEqualTo(l.<Integer>get("nroDosesDoLote"), 
//					filtro.getNroDosesDoLoteFinal()));
//		}
//		
//		if (filtro.getNroDosesAtualInicial() != null) {
//			predicateList.add(builder.greaterThanOrEqualTo(l.<Integer>get("nroDosesAtual"), 
//					filtro.getNroDosesAtualInicial()));
//		}
//		if (filtro.getNroDosesAtualFinal() != null) {
//			predicateList.add(builder.lessThanOrEqualTo(l.<Integer>get("nroDosesAtual"), 
//					filtro.getNroDosesAtualFinal()));
//		}
//		
//		if (filtro.getCodigoVacina() != null) {
//			predicateList.add(builder.equal(l.<Quarto>get("vacina").<Long>get("codigo"), 
//					filtro.getCodigoVacina()));
//		}
//		
//		predicateList.add(builder.equal(l.<Status>get("status"), Status.ATIVO));
//				
//		Predicate[] predArray = new Predicate[predicateList.size()];
//		predicateList.toArray(predArray);
//
//		criteriaQuery.select(l).where(predArray);
//		
//		PaginacaoUtil.prepararOrdem(l, criteriaQuery, builder, pageable);
//		
//		typedQuery = manager.createQuery(criteriaQuery);
//						
//		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
//		
//		List<Lote> vacinas = typedQuery.getResultList();
//		
//		long totalLotes = PaginacaoUtil.getTotalRegistros(l, predArray, builder, manager);
//		Page<Lote> page = new PageImpl<>(vacinas, pageable, totalLotes); 
//		
//		return page;
//	}
//
//}
