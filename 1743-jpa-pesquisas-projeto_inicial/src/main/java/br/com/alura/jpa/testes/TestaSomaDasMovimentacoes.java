package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaSomaDasMovimentacoes {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
		
		//select m from Movimentacao m
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		//select sum(m.valor)
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor"));
		query.select(sum);
		
		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);
		
		System.out.println("A soma das movimentações é: " + typedQuery.getSingleResult());
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
//		EntityManager em = emf.createEntityManager();
		
		//String jpql = "select sum(m.valor) from Movimentacao m";
//		String jpql2 = "select avg(m.valor) from Movimentacao m";
		
		//BigDecimal somaDasMovimentacoes = query.getSingleResult();
//		TypedQuery<Double> query2 = em.createQuery(jpql2, Double.class);
//		Double mediaDasMovimentacoes = query2.getSingleResult();
		
		//System.out.println("A soma das movimentações é: " + somaDasMovimentacoes);
//		System.out.println("A média das movimentações é: " + mediaDasMovimentacoes);
	}
	/*
	 * JPQL possui os tipico funções de agregação do mundo SQL / EX: SUM, AVG, MIN, MAX ou COUNT
	 */
}
