package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity // para se tornar uma tabela no dbo se usa entity
public class telefoneUser {
	
	@Id //tornando uma primary key
	@GeneratedValue (strategy = GenerationType.AUTO) // geranto o id automaticamente
	private Long id;
	
	@Column(nullable = false) // nao deixa o campo ser nulo (null not) obriga
	private String tipo;
	@Column(nullable = false) // nao deixa o campo ser nulo (null not) obriga
	private String numero;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER) // um para muitos e obrigando a sempre carregar ele
	private usuarioPessoa usuarioPessoa; // ao mesmo tempo faz uma foreign key

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public usuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(usuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}
	
	

}
