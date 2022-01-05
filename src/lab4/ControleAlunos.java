package lab4;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
 * Classe respons�vel por fazer o controle e gerenciar os alunos e os grupos de estudo.
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class ControleAlunos {
	
	private HashMap<String, Aluno> alunos;
	private HashMap<String, Grupo> grupos; 
	private ArrayList<Aluno> alunosQueRespoderamQuestoes;
	
	/**
	 * Cria o controle com os alunos, os grupos e uma lista dos alunos que respoderam as quest�es.
	 */
	public ControleAlunos() {
		this.alunos = new HashMap<>();
		this.grupos = new HashMap<>();
		this.alunosQueRespoderamQuestoes = new ArrayList<>();
	}
	
	/**
	 * Cadastra um aluno. � necess�rio informar a matr�cula, o nome e o curso. 
	 * 
	 * @throws UnsupportedOperationException Se a matr�cula j� estiver cadastrada.
	 * @param matricula Matr�cula do aluno.
	 * @param nome Nome do aluno.
	 * @param curso Curso do aluno.
	 * @return Retorna o booleano true se o cadastro for feito com sucesso. 
	 */
	
	public boolean cadastraAluno(String matricula, String nome, String curso) {		
		
		if(alunoExiste(matricula) == true)
			throw new UnsupportedOperationException("MATR�CULA J� CADASTRADA!");
		
		this.alunos.put(matricula, new Aluno(matricula, nome, curso));
		
		return true; 
	}
	
	/**
	 * Verifica se existe aluno com a matr�cula. 
	 * @param matricula Matr�cula do aluno
	 * @return Retorna true se matr�cula existir. Caso cont�rio, retorna false. 
	 * 
	 */
	public boolean alunoExiste(String matricula) {
		return this.alunos.containsKey(matricula);		
	}
	
	/**
	 * Verifica se o grupo existe.
	 * 
	 * @param nomeDoGrupo Nome do grupo
	 * @return Retorna true se o grupo existir. Caso contr�rio, retorna false.
	 */
	public boolean grupoExiste(String nomeDoGrupo) {
		return this.grupos.containsKey(nomeDoGrupo);
	}
	
	/**
	 * Consulta se um aluno pela sua matr�cula. Caso n�o exista aluno com a matr�cula informada 
	 * lan�a uma exce��o "Aluno n�o cadastrado." Caso contr�rio, retorna a matr�culo - nome - curso. 
	 * 
	 * @param matricula Matricula do aluno.
	 * @return Retorna uma String com os dados do aluno formatado.
	 */
	public String consultaAluno(String matricula) {
			
		if(alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno n�o cadastrado.");
		
		return "Aluno: " + this.alunos.get(matricula).toString() + "\n";
	}
	
	/**
	 * Cadastra um grupo. Todo grupo possui um nome, e pode haver restri��o de curso ou n�o. 
	 * 
	 * @throws UnsupportedOperationException Se o grupo j� estiver cadastrado. 
	 * @param nomeDoGrupo Nome do Grupo
	 * @param restricao Restri��o de curso
	 * @return Retorna true se o cadastro for efetuado. 
	 */
	
	public boolean cadastraGrupo(String nomeDoGrupo, String restricao) {
		
		if (grupoExiste(nomeDoGrupo) == true)
			throw new UnsupportedOperationException("GRUPO J� CADASTRADO!");
		
		this.grupos.put(nomeDoGrupo.toLowerCase(), new Grupo(nomeDoGrupo, restricao));
		
		return true;		
	}
	
	/**
	 * Aloca um aluno em um grupo de acordo com sua matr�cula e grupo de escolha. Caso o grupo tenha restri��o de curso,
	 * � lan�ado uma exce��o informando que o grupo tem restri��o.
	 * 
	 * @throws NoSuchElementException Se o aluno ou o grupo escolhido n�o estiver cadastrado. 
	 * @throws UnsupportedOperationException Se o grupo possuir restri��o.
	 * @param matricula Matr�cula do aluno.
	 * @param nomeDoGrupo Nome do grupo que deseja alocar aluno.
	 * @return Retorna true se o aluno for alocado com sucesso. 
	 */
	public boolean alocaAluno(String matricula, String nomeDoGrupo) {
		
		if(alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno n�o cadastrado.");
		if(grupoExiste(nomeDoGrupo) == false)
			throw new NoSuchElementException("Grupo n�o cadastrado.");
		
		if(verificaSeNaoPossuiRestricao(nomeDoGrupo) == false) {				
			if (this.grupos.get(nomeDoGrupo).getRestricao().equals(alunos.get(matricula).getCurso())) 	{							
				this.grupos.get(nomeDoGrupo).adicionaAluno(this.alunos.get(matricula));							
		}else {			
			throw new UnsupportedOperationException("GRUPO POSSUI RESTRI��O!");
			}
		}
		return true;
	}
	
	/**
	 * M�todo criado pra verificar se um grupo possui restri��o, fazendo uma verifica��o se a entrada foi nula. 
	 * Se a restri��o for nula retorna true, caso contr�rio, significa que o grupo possui restri��o. 
	 * 
	 * @param nomeDoGrupo Nome do grupo 
	 * @return Retorna um false se o grupo possui restri��o. 
	 */
	public boolean verificaSeNaoPossuiRestricao(String nomeDoGrupo) {
		return this.grupos.get(nomeDoGrupo).getRestricao().isBlank();
	}
	
	/**
	 * Verifica se um aluno pertence a um grupo. 
	 * 
	 * @throws NoSuchElementException Se o grupo n�o estiver cadastrado. Ou se o aluno n�o estiver no grupo.  
	 * @param nomeDoGrupo Nome do grupo.
	 * @param matricula Matr�cula do aluno. 
	 * 
	 * @return Retorna true se o aluno estiver no grupo. 
	 */
	public boolean pertenceAoGrupo(String nomeDoGrupo, String matricula) {
		
		if(grupoExiste(nomeDoGrupo) == false)
			throw new NoSuchElementException("Grupo n�o cadastrado.");
		
		if (this.grupos.get(nomeDoGrupo).verificaMatriculasNoGrupo().contains(matricula)) {
				return true; 
		}else {
			throw new NoSuchElementException("Aluno n�o est� no grupo.");
			}
	}
	
	/**
	 * Registra o aluno que responder quest�o, de acordo com sua matr�cula. Os alunos que responderam quest�es s�o adicionados em uma lista. 
	 * Caso a matr�cula informada n�o exista, lan�a uma exce��o informando "Aluno n�o cadastrado.". 
	 * 
	 * @throws NoSuchElementException Se o aluno n�o estiver cadastrado.
	 * @param matricula Matr�cula do aluno.
	 * @return Retorna true se o aluno for registrado com sucesso. 
	 */
	public boolean registraAlunosQueResponderamQuestao(String matricula) {
		
		if (alunoExiste(matricula) == false)
			throw new NoSuchElementException("Aluno n�o cadastrado.");
		
		this.alunosQueRespoderamQuestoes.add(this.alunos.get(matricula));
		
		return true; 
	}
	
	/**
	 * Imprime uma lista com todos os alunos que respoderam quest�o. 
	 * Seguindo a formata��o "matricula - nome - curso". Como o exemplo abaixo: 
	 * 1. 623 - Harry - Letras
	 * 2. 123 - Bellatrix - Engenharia Qu�mica
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
	 * Mostra a contagem de grupos com restri��o de curso. 
	 * Seguindo a formata��o "curso contagem". Como o exemplo abaixo: 
	 *  Educa��o F�sica 4
	 *  
	 * @param curso Nome do curso escolhido pra verificar contagem de restri��o.
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
