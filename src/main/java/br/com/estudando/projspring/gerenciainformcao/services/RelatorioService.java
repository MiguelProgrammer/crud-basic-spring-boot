package br.com.estudando.projspring.gerenciainformcao.services;

import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudando.projspring.gerenciainformcao.entidade.Aluno;
import br.com.estudando.projspring.gerenciainformcao.entidade.Disciplina;
import br.com.estudando.projspring.gerenciainformcao.repostories.AlunoRepository;

@Service
public class RelatorioService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Transactional
	public void menu(Scanner input) {

		Boolean isTrue = true;

		while (isTrue) {
			System.out.println("\nMENU - Escola EFMI - #Relatorios#\n");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Alunos por nome.");  
			System.out.println("2 - Alunos por nome e Idade.");  

			int opcao = input.nextInt();

			switch (opcao) {
			case 1:
				this.alunosPorNome(input);
				break; 
			case 2:
				this.procuraAlunoPorNomeMaioresDeIdade(input);
				break; 

			default:
				isTrue = false;
				System.out.println("Fim do Programa!");
				break;
			}
		}
	}

	private void procuraAlunoPorNomeMaioresDeIdade(Scanner input) {
		
		System.out.println("Digite o nome do aluno!");
		String nome = input.next();
		
		System.out.println("Digite a idade do aluno!");
		Integer idade = input.nextInt();
		
		List<Aluno> alunos = alunoRepository.findAlunoPorNomeMaioresDeIdade(nome,idade);
		
		if (!alunos.isEmpty()) {
		

			for (Aluno aluno : alunos) {
				System.out.println(
				"ID: " + aluno.getNome() +
				"\nNome: " + aluno.getNome() +
				"\nIdade: " + aluno.getIdade()
				);
			}
		} else {
			System.err.println("Aluno não ecnontrado.");
		}
		
	}

	private void alunosPorNome(Scanner input) { 
		
		System.out.println("Digite o nome do aluno!");
		String nome = input.next();
		
		List<Aluno> alunos = alunoRepository.findByNome(nome);
		
		alunos.forEach(aluno->System.out.println(
				"ID: " + aluno.getNome() +
				"\nNome: " + aluno.getNome() +
				"\nIdade: " + aluno.getIdade()
				)
				);
	}
	 
}



