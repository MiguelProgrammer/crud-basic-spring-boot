package br.com.estudando.projspring.gerenciainformcao.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estudando.projspring.gerenciainformcao.entidade.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

}
