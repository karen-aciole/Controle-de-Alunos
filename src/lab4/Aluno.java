package lab4;

import java.util.Objects;
/**
 * Representa um aluno. Todo aluno � identificado pelo nome, matr�cula e curso. 
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
public class Aluno {

	private String nome;
	private String matricula;
	private String curso;
	
	/**
	 * Cria o aluno. 
	 * 
	 * @throws IllegalArgumentException se a matr�cula, nome ou curso for nulo. 
	 * @param nome Nome do aluno.
	 * @param matricula Matr�cula do aluno.
	 * @param curso Curso do aluno. 
	 */
	public Aluno(String matricula, String nome, String curso) {
		if(matricula == null || matricula.isBlank())
			throw new IllegalArgumentException("Matr�cula inv�lida.");
		if(nome == null || nome.isBlank())
			throw new IllegalArgumentException("Nome inv�lido.");
		
		if(curso == null || curso.isBlank())
			throw new IllegalArgumentException("Curso inv�lido.");

		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso; 
	}
	
	/**
	 * Pega o curso do aluno.
	 * 
	 * @return Retorna uma String com o curso.
	 */
	public String getCurso() {
		return this.curso.toLowerCase(); 
	}
	
	/**
	 * Pega a matr�cula do aluno.
	 * 
	 * @return Retorna uma String com a matr�cula.
	 */
	public String getMatricula() {
		return this.matricula;
	}
	
	/**
	 * Formata��o dos dados do aluno. 
	 * Exemplo de sa�da: 
	 * 821 - Dumbledore - Hist�ria
	 * 
	 * @return Retorna uma String formatada com os dados do aluno. 
	 */
	@Override
	public String toString() {
		return this.matricula + " - "  + this.nome + " - " + this.curso;
	}

	
	/**
	 * Gera um hashCode da matr�cula do aluno. 
	 * 
	 * @return Retorna o hashCode da matr�cula. 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	/**
	 * Verifica se dois alunos s�o iguais. Eles ser�o iguais se possu�rem a mesma matr�cula.
	 * 
	 * @return Retorna um valor booleano indicando se s�o iguais ou n�o. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true; 
		
		if (obj == null || getClass() != obj.getClass()) 
			return false; 	
		
		Aluno other = (Aluno) obj;
		return Objects.equals(matricula, other.matricula);
	}
		
}
