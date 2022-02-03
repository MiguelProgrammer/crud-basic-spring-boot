package br.com.estudando.projspring.gerenciainformcao.services;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudando.projspring.gerenciainformcao.entidade.Disciplina;
import br.com.estudando.projspring.gerenciainformcao.entidade.Professor;
import br.com.estudando.projspring.gerenciainformcao.repostories.DisciplinaRepository;
import br.com.estudando.projspring.gerenciainformcao.repostories.ProfessorRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository discRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	public void menu(Scanner input) {

		Boolean isTrue = true;

		while (isTrue) {
			System.out.println("\nMENU - Escola EFMI - #Disciplinas#\n");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novA disciplina");
			System.out.println("2 - Atualizar disciplina");
			System.out.println("3 - Listar disciplinas");
			System.out.println("4 - Remover disciplina");

			int opcao = input.nextInt();

			switch (opcao) {
			case 1:
				this.cadastrar(input);
				break;
			case 2:
				this.atualizar(input);
				break;
			case 3:
				this.listar(input);
				break;
			case 4:
				this.deletar(input);
				break;

			default:
				isTrue = false;
				System.out.println("Fim do Programa!");
				break;
			}
		}
	}

	private void deletar(Scanner input) {

		System.out.print("\nDigite o id da disciplina para deletar.");
		Long idDisciplina = input.nextLong();

		Optional<Disciplina> objectOptional = discRepository.findById(idDisciplina);
		if (objectOptional.isPresent()) {

			Disciplina prof = objectOptional.get();
			discRepository.delete(prof);
			System.out.println("disciplina deletada.");

		} else {
			System.out.println("disciplina não encontrado: " + idDisciplina);
		}
	}

	private void listar(Scanner input) {
		Iterable<Disciplina> disciplinas = discRepository.findAll();

		System.out.println("disciplinaes.\n\n");
		for (Disciplina disciplina : disciplinas) {
			System.out.println("ID: " + disciplina.getId());
			System.out.println("Nome: " + disciplina.getNome());
			System.out.println("Semestre: " + disciplina.getSemestre());
			try {
				Professor professor = professorRepository.findById(disciplina.getProfessor().getId()).orElseThrow();
				System.out.println("Professor: " + professor.getId() + ": " + professor.getNome());
			} catch (NullPointerException e) {
				System.out.println("Id do Professor anulado!\n");
			} 
		}

	}

	private void cadastrar(Scanner input) {

		System.out.print("\nDigite o nome da disciplina.");
		String nomeDisciplina = input.next();

		System.out.print("\nDigite o semestre.");
		Integer semestre = input.nextInt();

		System.out.print("\nDigite o ID do professor.");
		Long idProfessor = input.nextLong();

		Disciplina disciplina = null;

		try {
			disciplina = new Disciplina(nomeDisciplina, semestre, null);
			disciplina.setProfessor(professorRepository.findById(idProfessor).orElse(null));
		} catch (Exception e) {
			System.out.println("Professor não encontrado!");
		}

		discRepository.save(disciplina);
		System.out.println("\nDisciplina cadastrada.");
	}

	private void atualizar(Scanner input) {

		System.out.print("\nDigite o id do disciplina para atualizar.");
		Long idDisciplina = input.nextLong();

		Disciplina disciplina = discRepository.findById(idDisciplina).orElse(null);
		if (!disciplina.getNome().isEmpty()) {

			System.out.print("\nDigite o nome da disciplina.");
			String nomedisciplina = input.next();
			disciplina.setNome(nomedisciplina);

			System.out.print("\nDigite o ID do professor.");
			Long idProfessor = input.nextLong();

			Professor professor = professorRepository.findById(idProfessor).orElse(null);
			if (!professor.getNome().isEmpty()) {
				disciplina.setProfessor(professor);
				discRepository.save(disciplina);
				System.out.println("disciplina atualizado.");
			} else {
				System.out.println("Erro ao atualizar disciplina.");
			}

		} else {
			System.out.println("disciplina não encontrada: " + idDisciplina);
		}

	}

}
