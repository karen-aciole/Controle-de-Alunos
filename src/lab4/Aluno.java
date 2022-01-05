package lab4;

import java.util.Objects;
/**
 * Representa um aluno. Todo aluno é identificado pelo nome, matrícula e curso. 
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
	 * @throws IllegalArgumentException se a matrícula, nome ou curso for nulo. 
	 * @param nome Nome do aluno.
	 * @param matricula Matrícula do aluno.
	 * @param curso Curso do aluno. 
	 */
	public Aluno(String matricula, String nome, String curso) {
		if(matricula == null || matricula.isBlank())
			throw new IllegalArgumentException("Matrícula inválida.");
		if(nome == null || nome.isBlank())
			throw new IllegalArgumentException("Nome inválido.");
		
		if(curso == null || curso.isBlank())
			throw new IllegalArgumentException("Curso inválido.");

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
	 * Pega a matrícula do aluno.
	 * 
	 * @return Retorna uma String com a matrícula.
	 */
	public String getMatricula() {
		return this.matricula;
	}
	
	/**
	 * Formatação dos dados do aluno. 
	 * Exemplo de saída: 
	 * 821 - Dumbledore - História
	 * 
	 * @return Retorna uma String formatada com os dados do aluno. 
	 */
	@Override
	public String toString() {
		return this.matricula + " - "  + this.nome + " - " + this.curso;
	}

	
	/**
	 * Gera um hashCode da matrícula do aluno. 
	 * 
	 * @return Retorna o hashCode da matrícula. 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	/**
	 * Verifica se dois alunos são iguais. Eles serão iguais se possuírem a mesma matrícula.
	 * 
	 * @return Retorna um valor booleano indicando se são iguais ou não. 
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
