package model;

import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;

@Entity
@javax.persistence.NamedQueries({
	@NamedQuery(name = "usuraioPessoa.consulTodos", query = "select u from usuarioPessoa u"), // named query para chamar query de algum lugar do codigo pelo name
	@NamedQuery(name = "usuarioPessoa.buscaPorNome", query = "select u from usuarioPessoa u where u.nome = :nome")
})

public class usuarioPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String sobrenome;
	private String email;
	private String login;
	private String senha;
	private int idade;
	
	@OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.EAGER) // mapeando os telefones por usuario
	private List<telefoneUser> telefoneUser; // criando lista de usuarios pois podem ser varios para um
	
	public void setTelefoneUser(List<telefoneUser> telefoneUser) {
		this.telefoneUser = telefoneUser;
	}
	
	public List<telefoneUser> getTelefoneUser() {
		return telefoneUser;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "usuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", login=" + login + ", senha=" + senha + ", idade=" + idade + "]";
	}

}
