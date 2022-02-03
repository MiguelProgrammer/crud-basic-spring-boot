package br.com.estudando.projspring.gerenciainformcao.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private Integer semestre;
	
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = true)
	private Professor professor;
	
	@ManyToMany
	@JoinTable(name = "disciplinas_alunos", 
		joinColumns = @JoinColumn(name = "disciplina_fk"),
		inverseJoinColumns = @JoinColumn(name = "aluno_fk")
	)
	private Set<Aluno> alunos;
	
	public Disciplina() { }

	public Disciplina(String nome, Integer semestre, Professor professor) { 
		this.nome = nome;
		this.semestre = semestre;
		this.professor = professor;
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

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	
	@Override
	public String toString() { 
		return super.toString();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
