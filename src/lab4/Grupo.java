package lab4;

import java.util.HashSet;
import java.util.Objects;

/**
 * Representa um grupo. Todo grupo possui um nome e alunos pertecentes ao grupo.
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class Grupo {
	
	/**
	 * Nome do grupo.
	 */
	private String nomeDoGrupo;
	
	/**
	 * Restri��o de curso.
	 */
	private String restricao; 
	
	/**
	 * Alunos matriculados 
	 */
	private HashSet <Aluno> alunos;
	
	/**
	 * Cria um grupo. Todo grupo possui um nome, alunos que est�o no grupo e pode possuir restri��o de curso. 
	 * 
	 * @throws IllegalArgumentException Se o nome do grupo for nulo ou vazio.
	 * @param nomeDoGrupo Nome do grupo.
	 * @param restricao Restri��o de curso. 
	 */
	public Grupo(String nomeDoGrupo, String restricao) {
		if(nomeDoGrupo == null || nomeDoGrupo.isBlank())
			throw new IllegalArgumentException("Nome do grupo inv�lido.");
		
		this.nomeDoGrupo = nomeDoGrupo;
		this.restricao = restricao; 
		this.alunos = new HashSet<>();
	}
	
	/**
	 * Adiciona aluno em um grupo.
	 * 
	 * @param aluno Aluno
	 */
	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);

	}
	
	/**
	 * Pega restri��o.
	 * 
	 * @return Uma String da restri��o.
	 */
	public String getRestricao() {
		return this.restricao.toLowerCase();
	}
	
	/**
	 * Verifica os alunos existentes em um grupo, s�o listada as matr�culas dos alunos.
	 * 
	 * @return String com as matr�culas.
	 */
	public String verificaMatriculasNoGrupo() {
		String matriculas = ""; 
		if(this.alunos != null) {
			for(Aluno aluno : this.alunos)
				matriculas += aluno.getMatricula() + "\n";			
		}
		return matriculas;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nomeDoGrupo.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true; 
		if (obj == null || getClass() != obj.getClass()) 
			return false; 
		
		Grupo other = (Grupo) obj;
		return Objects.equals(nomeDoGrupo.toLowerCase(), other.nomeDoGrupo.toLowerCase());
	}

}
