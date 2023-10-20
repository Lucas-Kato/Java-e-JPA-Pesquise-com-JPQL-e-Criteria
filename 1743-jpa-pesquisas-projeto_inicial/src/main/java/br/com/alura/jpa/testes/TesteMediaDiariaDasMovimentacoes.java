package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TesteMediaDiariaDasMovimentacoes {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		List<MediaComData> mediaDasMovimentacoes = new MovimentacaoDao(em).getMediaDiariaDasMovimentacoes();
		
		for (MediaComData resultado : mediaDasMovimentacoes) {
			System.out.println(
					"A média das moviemntacões do dia " + resultado.getDia() + "/" + resultado.getMes()  + " é : " + resultado.getValor());
		}

		/*
		 * JPQL também possui a cláusula group by que pode ser usado em combinação com as funções de agregação
		 * Como exectutar projeções e "instaciar objetos" já pelo JPQL*/
	}
}
