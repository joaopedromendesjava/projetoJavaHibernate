package projetoJavaHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	//Classe que lê o arquivo persistence e deixar a conexão com o banco de dados instanciado
	
		public static EntityManagerFactory factory = null;

		static {
			init(); 
		}
		
		private static void init() { //só pode ser lido uma vez
			try {
			
				if(factory== null) {
					factory = Persistence.createEntityManagerFactory("projetoJavaHibernate");
					
				}
			
			}catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		public static EntityManager geEntityManager() {
			
			return factory.createEntityManager(); // Provê a parte de persistencia
		}
		
		public static Object getPrimaryKey(Object entity) { // retorna a primary key
			return factory.getPersistenceUnitUtil().getIdentifier(entity);
			
		}
		
	}


