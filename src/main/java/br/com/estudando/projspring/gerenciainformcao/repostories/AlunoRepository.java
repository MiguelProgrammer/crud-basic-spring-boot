package br.com.estudando.projspring.gerenciainformcao.repostories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.estudando.projspring.gerenciainformcao.entidade.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	List<Aluno> findByNome(String nome);

	@Query(nativeQuery = true,
			value = "SELECT * FROM alunos WHERE nome like :nome% AND idade >= :idade")
	List<Aluno> findAlunoPorNomeMaioresDeIdade(String nome, Integer idade);
	
}
