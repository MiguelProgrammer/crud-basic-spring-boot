package br.com.estudando.projspring.gerenciainformcao.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudando.projspring.gerenciainformcao.entidade.Disciplina;
import br.com.estudando.projspring.gerenciainformcao.entidade.Professor;
import br.com.estudando.projspring.gerenciainformcao.repostories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository profRepository;

	@Transactional
	public void menu(Scanner input) {

		Boolean isTrue = true;

		while (isTrue) {
			System.out.println("\nMENU - Escola EFMI - #Professores#\n");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo professor");
			System.out.println("2 - Atualizar professor");
			System.out.println("3 - Listar professores");
			System.out.println("4 - Listar Por ID");
			System.out.println("5 - Remover professor");

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
				this.listarPorId(input);
				break;
			case 5:
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

		System.out.print("\nDigite o id do professor para deletar.");
		Long idProfessor = input.nextLong();

		Optional<Professor> objectOptional = profRepository.findById(idProfessor);
		if (objectOptional.isPresent()) {

			Professor prof = objectOptional.get();
			profRepository.delete(prof);
			System.out.println("Professor deletado.");

		} else {
			System.out.println("Professor não encontrado: " + idProfessor);
		}
	}

	private void listar(Scanner input) {
		Iterable<Professor> professores = profRepository.findAll();

		System.out.println("Professores.\n\n");
		for (Professor professor : professores) {
			System.out.println("ID: " + professor.getId());
			System.out.println("Nome: " + professor.getNome());
			System.out.println("Prontuário: " + professor.getProntuario());
		}
	}

	@Transactional
	private void listarPorId(Scanner input) {

		System.out.print("Digite o ID do Professor.");
		Long idProfessor = input.nextLong();

		Professor professor = profRepository.findById(idProfessor).orElse(null);

		List<Disciplina> listaDisciplinasProfessor = professor.getDisciplinas();

		System.out.println("Professores.\n\n");
		System.out.println("ID: " + professor.getId());
		System.out.println("Nome: " + professor.getNome());
		System.out.println("Prontuário: " + professor.getProntuario());
		for (Disciplina disciplina : listaDisciplinasProfessor) { 
			System.out.println("ID: " + disciplina.getId() + " - " + "Nome: " + disciplina.getNome() + " - "
					+ "Semestre: " + disciplina.getSemestre());

		}
	}

	private void cadastrar(Scanner input) {

		System.out.print("\nDigite o nome do professor.");
		String nomeProfessor = input.next();

		System.out.print("\nDigite o prontuário.");
		String prontuarioProfessor = input.next();

		profRepository.save(new Professor(nomeProfessor, prontuarioProfessor));

		System.out.println("Professor cadastrado.");
	}

	private void atualizar(Scanner input) {

		System.out.print("\nDigite o id do professor para atualziar.");
		Long idProfessor = input.nextLong();

		Optional<Professor> objectOptional = profRepository.findById(idProfessor);
		if (objectOptional.isPresent()) {

			System.out.print("\nDigite o nome do professor.");
			String nomeProfessor = input.next();

			Professor prof = objectOptional.get();
			prof.setNome(nomeProfessor);

			profRepository.save(prof);
			System.out.println("Professor atualizado.");

		} else {
			System.out.println("Professor não encontrado: " + idProfessor);
		}

	}

}
