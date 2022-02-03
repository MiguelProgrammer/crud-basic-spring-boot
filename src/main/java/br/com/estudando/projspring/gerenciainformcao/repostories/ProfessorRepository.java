package br.com.estudando.projspring.gerenciainformcao.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estudando.projspring.gerenciainformcao.entidade.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
