package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;

public class TestaRelatorioDasMovimentacoes {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		//para evitar elementos repetidos usa distinct
		//join fetch traz todos os resultados em uma única query
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
		
		List<Conta> contas = query.getResultList();
		for (Conta conta : contas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Agência: " + conta.getAgencia());
			System.out.println("Numero: " + conta.getNumero());
			System.out.println("Movimentações: " + conta.getMovimentacoes());
		}
		
		/*
		 * Relacionamento ToMany são Lazy por padrão
		 * Lazy significa que só serão carregados sob demanada
		 * Podemos reconfigurar esse comportamento pelo atributo fetch da anotação usando a enum FetchType.EAGER
		 * Nas queries podemos inicializar uma relacionamento lazy usando join fetch / para receber apenas resultados distintos podemos usar a palabra chave distinct
		 */
		
	}
}
