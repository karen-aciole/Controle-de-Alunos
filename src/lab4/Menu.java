package lab4;

import java.util.Scanner;

/**
 * Menu que gerencia as entradas do usuário.
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */

public class Menu {
	
	/**
	 * Criando o menu de escolhas.
	 */
	public static void main(String[] args) {
	
	ControleAlunos controleAlunos = new ControleAlunos();	
	Scanner scanner = new Scanner(System.in);
	String escolha = "";
	while (true) {
		escolha = menu(scanner);
		comando(escolha, controleAlunos, scanner);
		}	
	}
	
	/** 
	 * Menu de escolhas. 
	 * @param opcao
	 * @param controleAlunos
	 * @param scanner
	 */
	private static void comando(String opcao, ControleAlunos controleAlunos, Scanner scanner) {
		
		switch(opcao) {
		case "C": 
			cadastraAluno(controleAlunos, scanner);
			break;			
		case "E":
			exibeAluno(controleAlunos, scanner);
			break;
		case "N":
			cadastraGrupo(controleAlunos, scanner);
			break;
		case "A":
			escolha(controleAlunos, scanner);
			break;
		case "R": 
			registraAlunoQueRespondeu(controleAlunos, scanner);
			break;
		case "I": 
			imprimeAlunosQueRespoderam(controleAlunos, scanner);
			break;
		case "O":
			contagemDeGruposComRestricao(controleAlunos, scanner);
			break;
		case "S":
			System.out.println("Programa fechado!");
			System.exit(0);	
			break;
		default: 
			System.out.println("Opção inválida!");
		}
	}


	private static void contagemDeGruposComRestricao(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Curso: ");
		String curso = scanner.nextLine();
		System.out.println(controleAlunos.contagemDeGruposComRestricaoDeCurso(curso));		
	}


	private static void imprimeAlunosQueRespoderam(ControleAlunos controleAlunos, Scanner scanner) {
		System.out.println(controleAlunos.imprimeAlunosQueRespoderamQuestao());
	}


	private static void registraAlunoQueRespondeu(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		controleAlunos.registraAlunosQueResponderamQuestao(matricula);
		System.out.println("\nALUNO REGISTRADO!\n");
		
	}


	private static void alocaAluno(ControleAlunos controleAlunos, Scanner scanner) {
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		System.out.print("Grupo: ");
		String grupo = scanner.nextLine();
			
		controleAlunos.alocaAluno(matricula, grupo);
		System.out.println("ALUNO ALOCADO\n");	
		
	}

	private static void pertinenciaAGrupo(ControleAlunos controleAlunos, Scanner scanner) {
	
		System.out.print("Grupo: ");
		String grupo = scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		
		controleAlunos.pertenceAoGrupo(grupo, matricula);
		System.out.println("ALUNO PERTENCE AO GRUPO\n");
	}
	
	
	private static void cadastraGrupo(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Grupo: ");
		String grupo = scanner.nextLine();
		
		System.out.print("Restrição? ");
		String restricao = scanner.nextLine();
		
		controleAlunos.cadastraGrupo(grupo, restricao);
		System.out.println("CADASTRO REALIZADO!\n");
		
	}


	private static void exibeAluno(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();
		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();

		System.out.println(controleAlunos.consultaAluno(matricula));
		
	}


	private static void cadastraAluno(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();

		System.out.print("Matrícula: ");
		String matricula = scanner.nextLine();
		
		
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		
		System.out.print("Curso: ");
		String curso = scanner.nextLine();
		
		controleAlunos.cadastraAluno(matricula, nome, curso);
		System.out.println("\nCADASTRO REALIZADO!\n");
		
	}
	
	private static void escolha(ControleAlunos controleAlunos, Scanner scanner) {
		scanner.nextLine();
		System.out.print("(A)locar Aluno ou (P)ertinência a Grupo? ");
		String opcao = scanner.nextLine().toUpperCase();
		if (opcao.equals("A")) {
			alocaAluno(controleAlunos, scanner);
		}else if (opcao.equals("P")) {
			pertinenciaAGrupo(controleAlunos, scanner);
		}
	}
	
	private static String menu (Scanner scanner){ 
		
		System.out.println(
				"(C)adastrar Aluno\n"
				+ "(E)xibir Aluno\n"
				+ "(N)ovo Grupo\n"
				+ "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n"
				+ "(R)egistrar Aluno que Respondeu\n"
				+ "(I)mprimir Alunos que Responderam\n"
				+ "(O)xe, e a contagem dos grupos com restrição de curso?\n"
				+ "(S)im, quero fechar o programa!\n"
				+ "\n"
				+ "Opção> ");
		
		return scanner.next().toUpperCase();
	}
	
	
}
