package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaMovimentacoesFiltradasPorDataCriteria {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData = movimentacaoDao.getMovimentacoesFiltradasPorData(null, null, null);
		for (Movimentacao movimentacao : movimentacoesFiltradasPorData) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Valor -> " + movimentacao.getValor());
			System.out.println("---------------------------------------------");
		}
	}
	/*
	 * A JPQL deve ser usada para queries fixas que sempre buscam pelos mesmo parâmetros.
	 * Quando a query é dinâmica o JPQL perde a legibilidade e o código fica mais difícil de se manter / Para solucionar isso foi criado uma outra API de pesquisa, a CRITERIA API
	 * A criteria permite definir queries apenas com chamadas de métodos e assim possui mais flexibilidade quando os parâmetros variam
	 * Os protagonistas da criteria são 
	 * 				CriteriaQuery - a query em si, que possui os possui os médtodos principais como select(..), from(..) e where(..)
	 * 				CriteriaBuilder - uma classe auxiliar para definir filtros e projeções
	 * 				Root - para difinir os caminhos para atributos( m.data)*/
}
