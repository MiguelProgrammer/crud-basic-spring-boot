package br.com.estudando.projspring.gerenciainformcao.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String prontuario;
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)//, cascade = CascadeType.ALL
	private List<Disciplina> disciplinas;
	
	public Professor() { }

	public Professor(String nome, String prontuario) { 
		this.nome = nome;
		this.prontuario = prontuario;
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
	
	public String getProntuario() {
		return prontuario;
	}
	
	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}
	
	@Override
	public String toString() { 
		return super.toString();
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	@PreRemove
	public void preRemove() {
		for (Disciplina disciplina: this.disciplinas) {
			disciplina.setProfessor(null);
		}
	}
}
