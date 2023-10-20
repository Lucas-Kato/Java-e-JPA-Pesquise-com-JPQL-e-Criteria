package br.com.alura.jpa.modelo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.Movimentacao;

public class MovimentacaoDao {
	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Movimentacao> getMovimentacoesFiltradasPorData(Integer dia, Integer mes, Integer ano) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> query = builder.createQuery(Movimentacao.class);
		
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (dia != null) {
			//day(m.data)
			Predicate equal = builder.equal(builder.function("day", Integer.class, root.get("data")), dia);
			predicates.add(equal);
		}
		
		if (mes != null) {
			//month(m.data)
			Predicate equal = builder.equal(builder.function("month", Integer.class, root.get("data")), mes);
			predicates.add(equal);
		}
		
		if (ano != null) {
			//year(m.data)
			Predicate equal = builder.equal(builder.function("year", Integer.class, root.get("data")), ano);
			predicates.add(equal);
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Movimentacao> typerdQuery = em.createQuery(query);
		
		return typerdQuery.getResultList();
	}

	public List<MediaComData> getMediaDiariaDasMovimentacoes() {
		
		TypedQuery<MediaComData> query = em.createNamedQuery("mediaDiariaMovimentacoes", MediaComData.class);
		 return query.getResultList();
	}
	
	public BigDecimal getSomaDasMovimentacoes() {		
		String jpql = "select sum(m.valor) from Movimentacao m";
		
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
		BigDecimal somaDasMOviemntacoes = query.getSingleResult();
		
		return somaDasMOviemntacoes;
	}
	/*
	 * Mesmo com JPA faz sentido usar um DAO para encapsular as queries
	 * Em algumas bibliotecas chamam os DAO de repositórios(ex: Spring Data)
	 * O DAO deve receber o EntityManager como dependência(preferencialmente pelo construtor*/
	
}
