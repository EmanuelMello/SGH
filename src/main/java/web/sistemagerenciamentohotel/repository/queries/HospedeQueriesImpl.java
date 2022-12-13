package web.sistemagerenciamentohotel.repository.queries;

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

import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.HospedeFilter;
import web.sistemagerenciamentohotel.repository.pagination.PaginacaoUtil;

public class HospedeQueriesImpl implements HospedeQueries {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Hospede> pesquisar(HospedeFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Hospede> criteriaQuery = builder.createQuery(Hospede.class);
		Root<Hospede> h = criteriaQuery.from(Hospede.class);
		TypedQuery<Hospede> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getCodigo() != null) {
			predicateList.add(builder.equal(h.<Long>get("codigo"), filtro.getCodigo()));
		}
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(builder.lower(h.<String>get("nome")),
					"%" + filtro.getNome().toLowerCase() + "%"));
		}

		if (StringUtils.hasText(filtro.getCpf())) {
			predicateList.add(builder.like(builder.lower(h.<String>get("cpf")),
					"%" + filtro.getCpf().toLowerCase() + "%"));
		}
		
		if (StringUtils.hasText(filtro.getRg())) {
			predicateList.add(builder.like(builder.lower(h.<String>get("rg")),
					"%" + filtro.getRg().toLowerCase() + "%"));
		}
		
		if (StringUtils.hasText(filtro.getTelefone())) {
			predicateList.add(builder.like(builder.lower(h.<String>get("telefone")),
					"%" + filtro.getTelefone().toLowerCase() + "%"));
		}

		predicateList.add(builder.equal(h.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(h).where(predArray);

		PaginacaoUtil.prepararOrdem(h, criteriaQuery, builder, pageable);

		typedQuery = manager.createQuery(criteriaQuery);

		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Hospede> hospedes = typedQuery.getResultList();

		long totalHospedes = PaginacaoUtil.getTotalRegistros(h, predArray, builder, manager);
		Page<Hospede> page = new PageImpl<>(hospedes, pageable, totalHospedes);

		return page;
	}
}
