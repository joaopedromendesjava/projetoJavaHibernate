package projetoJavaHibernate;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import dao.daoGeneric;
import model.telefoneUser;
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
		@Test
		public void testeQueryListParameter() {
			daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>(); // passa a lista de usuario pessoa dentro do objeto do dao
			
			List<usuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from usuarioPessoa where nome = :nome")
					.setParameter("nome", "Nathalia Sarah").getResultList(); // faz uma query condicional com parametro utilizando a lista para retornar apenas o nome desejado
			
			for (usuarioPessoa usuarioPessoa : list) {
				 System.out.println(usuarioPessoa);
			}
		}
		@Test
		public void testeQuerySomaIdade() {
			daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>();
			
			long SomaIdade = (long) daoGeneric.getEntityManager().
					createQuery("select sum(u.idade) from usuarioPessoa u").getSingleResult(); // somando a idade de todos os usuarios identif. que recebe long 
			
			System.out.println("Soma de todas as idades é --->" +  SomaIdade);
		}
		
		@Test
		public void testeNamedQuery() {
			daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>();
			
			List<usuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("usuraioPessoa.consulTodos").getResultList(); // executa a named query que foi criada na classe usuarioPessoa
		
		for (usuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			}
		}
		
		@Test
		public void testeNamedQuery1() {
			daoGeneric<usuarioPessoa> daoGeneric = new daoGeneric<usuarioPessoa>();
			
			List<usuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("usuarioPessoa.buscaPorNome")
					.setParameter("nome", "Nome atualizado Hibernate").getResultList(); // executa a named query que foi criada na classe usuarioPessoa condição com parametro
		
		for (usuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			}
		}
		
		@Test
		public void testeGravaTelefone() {
			daoGeneric daoGeneric = new daoGeneric();
			
			usuarioPessoa pessoa = (usuarioPessoa) daoGeneric.pesquisar(27L, usuarioPessoa.class); // pesquisa a pessoa para carregar em memoria
			
			telefoneUser telefoneUser = new telefoneUser(); // vai instanicar os valores
			telefoneUser.setTipo("Casa");
			telefoneUser.setNumero("3133242081");
			telefoneUser.setUsuarioPessoa(pessoa);
			
			daoGeneric.salvar(telefoneUser); //salvando telefone em usuario pesquisado
			
		}
		
		@Test
		public void testeConsultaTelefones() {
			daoGeneric daoGeneric = new daoGeneric();
			
			usuarioPessoa pessoa = (usuarioPessoa) daoGeneric.pesquisar(27L, usuarioPessoa.class); // pesquisa a pessoa para carregar em memoria
			
			for (telefoneUser telefoneUser : pessoa.getTelefoneUser()) {
				
				System.out.println(telefoneUser.getNumero());
				System.out.println(telefoneUser.getTipo());
				System.out.println(telefoneUser.getUsuarioPessoa().getNome());
			
				System.out.println("-----------------------------------------------------------------------------");
				
			}
		
		
		
		}
	}



	
