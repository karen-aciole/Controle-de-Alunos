package lab4;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
 * Classe responsável por fazer o controle e gerenciar os alunos e os grupos de estudo.
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class ControleAlunos {
	
	private HashMap<String, Aluno> alunos;
	private HashMap<String, Grupo> grupos; 
	private ArrayList<Aluno> alunosQueRespoderamQuestoes;
	
	/**
	 * Cria o controle com os alunos, os grupos e uma lista dos alunos que respoderam as questões.
	 */
	public ControleAlunos() {
		this.alunos = new HashMap<>();
		this.grupos = new HashMap<>();
		this.alunosQueRespoderamQuestoes = new ArrayList<>();
	}
	
	/**
	 * Cadastra um aluno. É necessário informar a matrícula, o nome e o curso. 
	 * 
	 * @throws UnsupportedOperationException Se a matrícula já estiver cadastrada.
	 * @param matricula Matrícula do aluno.
	 * @param nome Nome do aluno.
	 * @param curso Curso do aluno.
	 * @return Retorna o booleano true se o cadastro for feito com sucesso. 
	 */
	
	public boolean cadastraAluno(String matricula, String nome, String curso) {		
		
		if(alunoExiste(matricula) == true)
			throw new UnsupportedOperationException("MATRÍCULA JÁ CADASTRADA!");
		
		this.alunos.put(matricula, new Aluno(matricula, nome, curso));
		
		return true; 
	}
	
	/**
	 * Verifica se existe aluno com a matrícula. 
	 * @param matricula Matrícula do aluno
	 * @return Retorna true se matrícula existir. Caso contário, retorna false. 
	 * 
	 */
	public boolean alunoExiste(String matricula) {
		return this.alunos.containsKey(matricula);		
	}
	
	/**
	 * Verifica se o grupo existe.
	 * 
	 * @param nomeDoGrupo Nome do grupo
	 * @return Retorna true se o grupo existir. Caso contrário, retorna false.
	 */
	public boolean grupoExiste(String nomeDoGrupo) {
		return this.grupos.containsKey(nomeDoGrupo);
	}
	
	/**
	 * Consulta se um aluno pela sua matrícula. Caso não exista aluno com a matrícula informada 
	 * lança uma exceção "Aluno não cadastrado." Caso contrário, retorna a matrículo - nome - curso. 
	 * 
	 * @param matricula Matricula do aluno.
	 * @return Retorna uma String com os dados do aluno formatado.
	 */
	public String consultaAluno(String matricula) {
			
		if(alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno não cadastrado.");
		
		return "Aluno: " + this.alunos.get(matricula).toString() + "\n";
	}
	
	/**
	 * Cadastra um grupo. Todo grupo possui um nome, e pode haver restrição de curso ou não. 
	 * 
	 * @throws UnsupportedOperationException Se o grupo já estiver cadastrado. 
	 * @param nomeDoGrupo Nome do Grupo
	 * @param restricao Restrição de curso
	 * @return Retorna true se o cadastro for efetuado. 
	 */
	
	public boolean cadastraGrupo(String nomeDoGrupo, String restricao) {
		
		if (grupoExiste(nomeDoGrupo) == true)
			throw new UnsupportedOperationException("GRUPO JÁ CADASTRADO!");
		
		this.grupos.put(nomeDoGrupo.toLowerCase(), new Grupo(nomeDoGrupo, restricao));
		
		return true;		
	}
	
	/**
	 * Aloca um aluno em um grupo de acordo com sua matrícula e grupo de escolha. Caso o grupo tenha restrição de curso,
	 * é lançado uma exceção informando que o grupo tem restrição.
	 * 
	 * @throws NoSuchElementException Se o aluno ou o grupo escolhido não estiver cadastrado. 
	 * @throws UnsupportedOperationException Se o grupo possuir restrição.
	 * @param matricula Matrícula do aluno.
	 * @param nomeDoGrupo Nome do grupo que deseja alocar aluno.
	 * @return Retorna true se o aluno for alocado com sucesso. 
	 */
	public boolean alocaAluno(String matricula, String nomeDoGrupo) {
		
		if(alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno não cadastrado.");
		if(grupoExiste(nomeDoGrupo) == false)
			throw new NoSuchElementException("Grupo não cadastrado.");
		
		if(verificaSeNaoPossuiRestricao(nomeDoGrupo) == false) {				
			if (this.grupos.get(nomeDoGrupo).getRestricao().equals(alunos.get(matricula).getCurso())) 	{							
				this.grupos.get(nomeDoGrupo).adicionaAluno(this.alunos.get(matricula));							
		}else {			
			throw new UnsupportedOperationException("GRUPO POSSUI RESTRIÇÃO!");
			}
		}
		return true;
	}
	
	/**
	 * Método criado pra verificar se um grupo possui restrição, fazendo uma verificação se a entrada foi nula. 
	 * Se a restrição for nula retorna true, caso contrário, significa que o grupo possui restrição. 
	 * 
	 * @param nomeDoGrupo Nome do grupo 
	 * @return Retorna um false se o grupo possui restrição. 
	 */
	public boolean verificaSeNaoPossuiRestricao(String nomeDoGrupo) {
		return this.grupos.get(nomeDoGrupo).getRestricao().isBlank();
	}
	
	/**
	 * Verifica se um aluno pertence a um grupo. 
	 * 
	 * @throws NoSuchElementException Se o grupo não estiver cadastrado. Ou se o aluno não estiver no grupo.  
	 * @param nomeDoGrupo Nome do grupo.
	 * @param matricula Matrícula do aluno. 
	 * 
	 * @return Retorna true se o aluno estiver no grupo. 
	 */
	public boolean pertenceAoGrupo(String nomeDoGrupo, String matricula) {
		
		if(grupoExiste(nomeDoGrupo) == false)
			throw new NoSuchElementException("Grupo não cadastrado.");
		
		if (this.grupos.get(nomeDoGrupo).verificaMatriculasNoGrupo().contains(matricula)) {
				return true; 
		}else {
			throw new NoSuchElementException("Aluno não está no grupo.");
			}
	}
	
	/**
	 * Registra o aluno que responder questão, de acordo com sua matrícula. Os alunos que responderam questões são adicionados em uma lista. 
	 * Caso a matrícula informada não exista, lança uma exceção informando "Aluno não cadastrado.". 
	 * 
	 * @throws NoSuchElementException Se o aluno não estiver cadastrado.
	 * @param matricula Matrícula do aluno.
	 * @return Retorna true se o aluno for registrado com sucesso. 
	 */
	public boolean registraAlunosQueResponderamQuestao(String matricula) {
		
		if (alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno não cadastrado.");
		
		this.alunosQueRespoderamQuestoes.add(this.alunos.get(matricula));
		
		return true; 
	}
	
	/**
	 * Imprime uma lista com todos os alunos que respoderam questão. 
	 * Seguindo a formatação "matricula - nome - curso". Como o exemplo abaixo: 
	 * 1. 623 - Harry - Letras
	 * 2. 123 - Bellatrix - Engenharia Química
	 * 3. 9100 - Snape - Biologia
	 * 
	 * @return String formatada com os dados dos alunos. 
	 */
	public String imprimeAlunosQueRespoderamQuestao() {
		String registro = "Alunos: \n";
		int pos = 1;
		for (Aluno alunos: alunosQueRespoderamQuestoes)
			registro += pos++ + ". " + alunos.toString() + "\n";
		
		return registro; 
	}
	
	/**
	 * Mostra a contagem de grupos com restrição de curso. 
	 * Seguindo a formatação "curso contagem". Como o exemplo abaixo: 
	 *  Educação Física 4
	 *  
	 * @param curso Nome do curso escolhido pra verificar contagem de restrição.
	 * @return String formatada com o nome do curso e a contagem.
	 */
	public String contagemDeGruposComRestricaoDeCurso(String curso) {		 		
		int restricao = 0; 
		String imprimeContagem = curso + " " + restricao + "\n";
		
		for (Grupo grupo: this.grupos.values()) {
			if(!grupo.getRestricao().isBlank() && grupo.getRestricao().equals(curso.toLowerCase())) {
				restricao++;
				imprimeContagem = curso + " " + restricao + "\n"; 				
			}
		}
		return imprimeContagem; 
	}
}
