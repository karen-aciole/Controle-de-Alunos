package lab4testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab4.Grupo;
import lab4.Aluno;

class GrupoTest {
	private Aluno aluno;
	private Grupo grupoTeste1; 
	private Grupo grupoTeste2; 
	
	@BeforeEach
	public void criaGrupos() {
		this.grupoTeste1 = new Grupo("Paramore", "m�sica");
		this.grupoTeste2 = new Grupo("Delta", null);		
	}
	
	/**
	 * Testa se uma exce��o � lan�ada quando o nome do grupo � vazio ou nulo. 
	 */
	@Test
	public void testaNomeDoGrupoInvalido() {
		try {
			new Grupo(null, "Biologia");
			new Grupo("", "Engenharia Civil");
			fail("Espera-se uma exce��o.");
		
		}catch(IllegalArgumentException iae) {
		}
	}
	
	/**
	 * Testa se um aluno foi adicionado no grupo. 
	 */
	@Test
	public void testaAdicionaAlunoEmGrupo() {
		this.aluno = new Aluno("1", "Sylvia", "Letras");
		grupoTeste1.adicionaAluno(aluno);
		assertEquals("1", this.grupoTeste1.verificaMatriculasNoGrupo());
		assertNotEquals("8", this.grupoTeste2.verificaMatriculasNoGrupo());
	}
	
	/**
	 * Testa se dois grupos diferentes possuem o mesmo hashcode. 
	 */
	@Test
	public void testaHashCodeGruposDiferentes() {
		assertNotEquals(this.grupoTeste1.hashCode(), this.grupoTeste2.hashCode());
	}
	
	/**
	 * Testa se dois grupos iguais possuem o mesmo hashcode. Ser�o iguais se os grupos possu�rem o mesmo nome. 
	 */
	@Test
	public void testaHashCodeGruposIguais() {
		assertEquals(this.grupoTeste1.hashCode(), new Grupo("paramore", null));
	}
	
	/**
	 * Testa se dois grupos s�o iguais. Ser�o iguais se possu�rem o mesmo nome.
	 */
	@Test
	public void testaEqualsObjects() {
		assertFalse(this.grupoTeste1.equals(new Grupo("Beatles", "")));
		assertTrue(this.grupoTeste2.equals(new Grupo("delta", "f�sica")));
	}
}