package br.com.estudando.projspring.gerenciainformcao.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudando.projspring.gerenciainformcao.entidade.Aluno;
import br.com.estudando.projspring.gerenciainformcao.entidade.Disciplina;
import br.com.estudando.projspring.gerenciainformcao.entidade.Professor;
import br.com.estudando.projspring.gerenciainformcao.repostories.AlunoRepository;
import br.com.estudando.projspring.gerenciainformcao.repostories.DisciplinaRepository;
import br.com.estudando.projspring.gerenciainformcao.repostories.ProfessorRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Transactional
	public void menu(Scanner input) {

		Boolean isTrue = true;

		while (isTrue) {
			System.out.println("\nMENU - Escola EFMI - #Alunos#\n");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar"); 
			System.out.println("2 - Listar");
			System.out.println("3 - Remover");
			System.out.println("4 - Listar um aluno");

			int opcao = input.nextInt();

			switch (opcao) {
			case 1:
				this.cadastrar(input);
				break;
			case 2:
				this.listar(input);
				break;
			case 3:
				this.deletar(input);
				break;
			case 4:
				this.listarPorId(input);
				break;

			default:
				isTrue = false;
				System.out.println("Fim do Programa!");
				break;
			}
		}
	}

	private void deletar(Scanner input) {

		System.out.print("\nDigite o id do aluno para deletar.");
		Long idAluno = input.nextLong();

		Aluno aluno = alunoRepository.findById(idAluno).orElse(null);

		if (!aluno.getNome().isEmpty()) {
			alunoRepository.delete(aluno);
			System.out.println("Professor deletado.");

		} else {
			System.out.println("Aluno não encontrado: " + idAluno);
		}
	}

	@Transactional
	private void listar(Scanner input) {
		List<Aluno> alunos = (List<Aluno>) alunoRepository.findAll();

		System.out.println("Alunos ****************.\n");
		for (Aluno aluno : alunos) {
			System.out.println("ID: " + aluno.getId());
			System.out.println("Nome: " + aluno.getNome());
			System.out.println("Idade: " + aluno.getIdade());

			if (aluno.getDisciplinas() != null) {
				System.out.println("DISCIPLINAS [ ");
				for (Disciplina disciplina : aluno.getDisciplinas()) {
					System.out.println("ID: " + disciplina.getId());
					System.out.println("Nome: " + disciplina.getNome());
					System.out.println("Semestre: " + disciplina.getSemestre());

					Professor professor = professorRepository.findById(disciplina.getProfessor().getId()).orElse(null);
					System.out.println("Professor: ID" + ":" + professor.getNome());
				}
				System.out.println(" ]");
			}
		}
	}

	@Transactional
	private void listarPorId(Scanner input) {

		System.out.print("Digite o ID do aluno.");
		Long idAluno = input.nextLong();

		Aluno aluno = alunoRepository.findById(idAluno).orElse(null);

		Set<Disciplina> listaDisciplinasAluno = aluno.getDisciplinas();

		System.out.println("Aluno ***********.\n\n");
		System.out.println("ID: " + aluno.getId());
		System.out.println("Nome: " + aluno.getNome());
		System.out.println("Prontuário: " + aluno.getIdade());
		for (Disciplina disciplina : listaDisciplinasAluno) {
			System.out.println("ID: " + disciplina.getId() + " - " + "Nome: " + disciplina.getNome() + " - "
					+ "Semestre: " + disciplina.getSemestre());

		}
	}

	private void cadastrar(Scanner input) {

		Set<Disciplina> novaGrade = new HashSet();

		/**
		 * Criando Entidade Aluno
		 */
		System.out.print("\nDigite o nome do aluno.");
		String nomeAluno = input.next();

		System.out.print("\nDigite a idade.");
		Integer idadeAluno = input.nextInt();

		System.out.print("\nDigite a quantidade de disciplinas que ele possue.");
		int grade = input.nextInt();

		System.out.println("Lista de Disciplinas ************");
		for (int i = 1; i <= grade; i++) {

			Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();
			for (Disciplina disciplina : disciplinas) {
				System.out.println("ID: " + disciplina.getId() + "Nome:  " + disciplina.getNome());
			}

			System.out.println("Digite o ID da disciplina N*" + i);
			Long disciplina = input.nextLong();

			Disciplina disciplinaAluno = disciplinaRepository.findById(disciplina).orElseThrow();
			novaGrade.add(disciplinaAluno);
		}

		Aluno aluno = new Aluno(nomeAluno, idadeAluno, novaGrade);
		alunoRepository.save(aluno);
		System.out.println("Aluno matriculado.");
	}
}
