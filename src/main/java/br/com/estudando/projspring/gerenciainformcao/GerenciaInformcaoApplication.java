package br.com.estudando.projspring.gerenciainformcao;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.estudando.projspring.gerenciainformcao.services.AlunoService;
import br.com.estudando.projspring.gerenciainformcao.services.DisciplinaService;
import br.com.estudando.projspring.gerenciainformcao.services.ProfessorService;
import br.com.estudando.projspring.gerenciainformcao.services.RelatorioService;

@SpringBootApplication
public class GerenciaInformcaoApplication implements CommandLineRunner {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	DisciplinaService disciplinaService;

	@Autowired
	AlunoService alunoService;
	
	@Autowired
	RelatorioService relatorioService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciaInformcaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner input = new Scanner(System.in);
		Boolean isTrue = true;
		
		while (isTrue) {
			System.out.println("\nMENU - Escola EFMI\n");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Discplina"); 
			System.out.println("3 - Aluno"); 
			System.out.println("4 - Relatorios"); 

			int opcao = input.nextInt();

			switch (opcao) {
			case 1:
				professorService.menu(input);
				break;
			case 2:
				disciplinaService.menu(input);
				break;
			case 3:
				alunoService.menu(input);
				break;
			case 4:
				relatorioService.menu(input);
				break;

			default:
				isTrue = false;
				System.out.println("Fim do Programa!");
				break;
			}

		}
	}

}
