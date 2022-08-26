package projetoJavaHibernate;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import dao.daoGeneric;
import model.usuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() { // metodo de salvar
		HibernateUtil.geEntityManager();
		
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // passando qual entidade vai persistir os dados no banco 
		
		usuarioPessoa pessoa = new usuarioPessoa();
		
		pessoa.setIdade(19);
		pessoa.setLogin("teste");
		pessoa.setNome("Nathalia Sarah");
		pessoa.setSenha("101020");
		pessoa.setEmail("nathaliribeiro447@gmail.com");
		pessoa.setSobrenome("ribeiro santos");
		
		daoGeneric.salvar(pessoa);		
		
	}
	
	
	@Test
	public void testeBuscar() { // metodo de buscar (pesquisar)
		
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // cria objeto da classe
		
		usuarioPessoa pessoa = new usuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() { // metodo de atualizar 
		
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // cria objeto da classe
		
		usuarioPessoa pessoa = new usuarioPessoa();
		
		pessoa.setIdade(99);// atualiza a nova idade 
		pessoa.setNome("Nome atualizado Hibernate"); // atualiza novo nome  
		pessoa.setSenha("123@");
		
		pessoa = daoGeneric.updateMerge(pessoa); // atualiza a pessoa que pegou por instancia da entidade e retorna a pessoa atualizada
		
		System.out.println(pessoa);
	}

	@Test
	public void testeDelete() { // metodo que deleta *Obs* para deletar primeiro irei consultar
		
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // cria objeto da classe
		
		usuarioPessoa pessoa = daoGeneric.pesquisar(5L , usuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
	}
	
	@Test
	public void testeConsultar() { // metodo que deleta *Obs* para deletar primeiro irei consultar
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // cria objeto da classe
		
		List<usuarioPessoa> list = daoGeneric.listar(usuarioPessoa.class); // chamo metodo listar com todos os dados
		
		for (usuarioPessoa usuarioPessoa : list) { // percorrer e imprimir todos os dados da lista desse objeto
			
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------------------------------------------------");
		
		}
	}
	@Test
	public void testeQueryList() { //metodo executa getentity fazendo a query que desejar
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>();
		
		List<usuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from usuarioPessoa").getResultList(); // realiza a query com os dados da lista da tabela
		
		for (usuarioPessoa usuarioPessoa : list) { // percorre e imprime o select
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListMaxResult() { //metodo executa getentity fazendo a query que desejar
		daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>();
		
		List<usuarioPessoa> list = daoGeneric.getEntityManager().
				createQuery("from usuarioPessoa order by id")
				.setMaxResults(4)
				.getResultList(); // realiza a query com os dados da lista da tabela
		
		for (usuarioPessoa usuarioPessoa : list) { // percorre e imprime o select
			System.out.println(usuarioPessoa);
		}
	}
	
	
	
	
}
