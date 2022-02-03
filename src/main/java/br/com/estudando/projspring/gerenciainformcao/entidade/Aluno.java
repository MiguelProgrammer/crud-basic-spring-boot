package br.com.estudando.projspring.gerenciainformcao.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	private Integer idade;

	@ManyToMany(mappedBy = "alunos")
	private Set<Disciplina> disciplinas;
	
	public Aluno() {}

	public Aluno(String nome, Integer idade, Set<Disciplina> disciplinas) {
		this.nome = nome;
		this.idade = idade;
		this.disciplinas = disciplinas;
	}
	
	public Long getId() {
		return id;
	} 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
}
