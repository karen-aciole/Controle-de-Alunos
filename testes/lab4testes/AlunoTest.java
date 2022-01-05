package lab4testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab4.Aluno;

class AlunoTest {
	
	private Aluno alunoTeste1;
	private Aluno alunoTeste2;
	private Aluno alunoTeste3; 
	
	@BeforeEach
	
	public void preparaAluno() {
		this.alunoTeste1 = new Aluno("5874", "Lady Gaga", "Artes");
		this.alunoTeste2 = new Aluno("1000", "Hozier", "Letras");
		this.alunoTeste3 = new Aluno("471", "Dumbledore", "filosofia");
	}
	
	/**
	 * Testa se a exceção é lançada quando a matrícula é vazia ou nula.
	 */
	@Test
	public void testaMatriculaInvalida() {
		try {
			new Aluno("", "Martin", "Matemática");
			new Aluno(null, "Zendaya", "Cinema");
			fail("Espera-se uma exceção.");
		
		}catch(IllegalArgumentException iae) {		
		}
	}
	
	/**
	 * Testa se a exceção é lançada quando o nome do aluno é vazio ou nulo. 
	 */
	@Test
	public void testaNomeDoAlunoInvalido() {
		try {
			new Aluno("14", " ", "Teatro");
			new Aluno("1", null, "Geografia");
			fail("Espera-se uma exceção.");
		
		}catch(IllegalArgumentException iae) {
		}
	}
	
	/**
	 * Testa se a exceção é lançada quando o curso é vazio ou nulo.
	 */
	@Test
	public void testaCursoInvalido() {
		try {
			new Aluno("111", "Tyler", "       ");
			new Aluno("9", "Mia", null); 
			fail("Espera-se uma exceção.");
		
		}catch(IllegalArgumentException iae) {
		}
	}
	
	/**
	 * Testa a saída do método que pega o curso do aluno.
	 */
	@Test
	public void testGetCurso() {
		assertEquals("letras", this.alunoTeste2.getCurso());
		assertEquals("artes", this.alunoTeste1.getCurso());
		assertEquals("filosofia", this.alunoTeste3.getCurso());
	}
	
	/**
	 * Testa a saída do método que pega a matrícula do aluno.
	 */
	@Test 
	public void testGetMatricula() {
		assertEquals("471", this.alunoTeste3.getMatricula());
		assertEquals("1000", this.alunoTeste2.getMatricula());
	}
	
	/**
	 * Testa formatação dos dados do aluno. 
	 */
	@Test
	public void testToString() {
		assertEquals("5874 - Lady Gaga - Artes", this.alunoTeste1.toString());
		assertEquals("777 - Hermione - Farmárcia", new Aluno("777", "Hermione", "Farmárcia").toString());
	}
	
	/**
	 * Testa se dois alunos diferentes possuem o mesmo hashcode. 
	 */
	@Test
	public void testaHashCodeAlunosDiferentes() {
		assertNotEquals(this.alunoTeste1.hashCode(), this.alunoTeste2.hashCode());
	}
	
	/**
	 * Testa se dois alunos iguais possuem o mesmo hashcode. Serão iguais se possuírem a mesma matrícula. 
	 */
	@Test
	public void testaHashCodeAlunosIguais() {
		assertEquals(this.alunoTeste2.hashCode(), new Aluno("1000", "Maria", "Quimica").hashCode());
	}
	
	/**
	 * Testa se dois alunos são iguais ou não. Serão iguais se possuírem a mesma matrícula.
	 */
	@Test
	public void testaEqualsObjects() {
		assertFalse(this.alunoTeste1.equals(this.alunoTeste2));
		assertTrue(this.alunoTeste3.equals(new Aluno("471", "Tarantino", "Arquitetura")));
	}
}
