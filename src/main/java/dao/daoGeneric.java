package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import projetoJavaHibernate.HibernateUtil;

public class daoGeneric<E> {
	
	private EntityManager entityManager = HibernateUtil.geEntityManager();
	
	public void salvar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin(); // dando comit na transação
		entityManager.persist(entidade); // persistindo, salvando
		transaction.commit(); // salvando no banco 
		
	}
	
	public E updateMerge(E entidade) { // salva ou atualiza
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin(); // dando comit na transação (startando)
			E entidadeSalva = entityManager.merge(entidade); // vai no banco salva e retorna(merge)
			transaction.commit(); // salvando no banco 
			
			return entidadeSalva; // retorna a entidade que foi gravada no banco
	}
	
	public E pesquisar(E entidade) {
		
		Object id = HibernateUtil.getPrimaryKey(entidade); // pega a entidade id para consultar
		
		E e = (E) entityManager.find(entidade.getClass(),id);
		
		return e;
	}
	
	public E pesquisar(Long id, Class<E> entidade) { // metodo de pesquisar por entidade
	
		E e = (E) entityManager.find(entidade, id);
		return e;

	}
	
	public void deletarPorId (E entidade) { // recebe a entidade
		
		Object id = HibernateUtil.getPrimaryKey(entidade); // metodo para identificar o id 
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin(); 
		
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() 
				+ " where id = " + id).executeUpdate(); //// deletando por id faz delete e atualiza
		
		transaction.commit(); //grava alteração no banco
		
	}
	
	public List<E> listar(Class<E> entidade){ // retorna uma lista buscando pela entidade passada
		
		EntityTransaction transaction = entityManager.getTransaction(); // instartando transação
		transaction.begin();
		
		List<E> list = entityManager.createQuery("from " + entidade.getName()).getResultList();//select form usuariopessoa trazendo todos os dados existentes na table
		
		transaction.commit();
		return list;
		
	}
	
	public EntityManager getEntityManager() { //get do entity manager para utilizar no teste
		return entityManager;
	}
	
	
	
	

}
