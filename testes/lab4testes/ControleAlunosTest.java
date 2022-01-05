package lab4testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import lab4.*;

/**
 *Classe que testa a classe de ControleAlunos. 
 * 
 * @author Karen Anne Aciole Alves - 119210934
 *
 */
class ControleAlunosTest {
	
	private ControleAlunos controleAluno; 
	
	
	@BeforeEach
	public void criaControleAluno() {
		this.controleAluno = new ControleAlunos();
		this.controleAluno.cadastraAluno("74", "Kim", "F�sica"); 
		this.controleAluno.cadastraAluno("999", "Hermione Granger","Qu�mica"); 
		this.controleAluno.cadastraGrupo("Twitch", "geografia");
	}
	
	/**
	 * Testa se o cadastro foi realizado com sucesso. 
	 */
	@Test
	public void testaSeCadastroDeAlunoFoiRealizado() {
		assertTrue(controleAluno.cadastraAluno("43", "Luna Lovegood","Ci�ncias Sociais"));
		assertTrue(controleAluno.cadastraAluno("90", "Neville Longbottom", "Biologia"));
	}
	
	/**
	 * Testa se uma exce��o � lan�ada quando um aluno � cadastrado com a mesma matr�cula de um aluno existente.
	 */
	@Test
	public void testaMatriculaJaCadastrada() {
		try {
		this.controleAluno.cadastraAluno("87", "Sirius Black", "Engenharia El�trica");
		this.controleAluno.cadastraAluno("87", "Bellatrix Lestrange", "Arquitetura");
		fail("Espera-se uma exce��o.");
		
		}catch(UnsupportedOperationException uoe){
		}
	}
	
	@Test
	/**
	 * Consulta os dados de um aluno cadastrado. 
	 */
	public void testaConsultaAluno() {	
		this.controleAluno.cadastraAluno("132", "Tom Riddle", "Qu�mica");
		assertEquals("Aluno: 132 - Tom Riddle - Qu�mica\n", this.controleAluno.consultaAluno("132"));
		
		this.controleAluno.cadastraAluno("99999", "James Potter", "F�sica");
		assertEquals("Aluno: 99999 - James Potter - F�sica\n", this.controleAluno.consultaAluno("99999"));
	}
	
	/**
	 * Testa se uma exce��o � lan�ada quando consulta um aluno n�o cadastrado.
	 */
	@Test
	public void testaConsultaAlunoInexistente() {
		try {
			this.controleAluno.consultaAluno("72");
			this.controleAluno.consultaAluno("01");
			fail("Espera-se uma exce��o.");
		}catch (NoSuchElementException nsex){			
		}
	}
	
	/**
	 * Testa se um grupo � cadastrado com sucesso.
	 */
	@Test
	public void testaCadastraGrupo() {
		assertTrue(controleAluno.cadastraGrupo("Lufa lufa", null)); 
		assertTrue(controleAluno.cadastraGrupo("Sonserina", "Sangue puro"));		
	}
	
	/**
	 * Testa se uma exce��o � lan�ada quando tenta cadastrar um grupo com um nome que j� foi cadastrado.
	 */
	@Test
	public void testaGrupoJaCadastrado() {
		try {
			this.controleAluno.cadastraGrupo("laranja", "Matem�tica"); 
			this.controleAluno.cadastraGrupo("laranja", "");
			fail("Espera-se uma exce��o.");
		
		}catch(UnsupportedOperationException uoe) {			
		}		
	}
	
	/**
	 * Testa se um aluno � alocado em um grupo. 
	 */
	@Test
	public void testaAlocaAluno() {
		this.controleAluno.cadastraAluno("77", "Ronald Weasley", "Geografia");
		assertTrue(this.controleAluno.alocaAluno("77", "twitch"));
	}
	
	/**
	 * Testa se uma exce��o � lan�ada quando tenta alocar um aluno de um curso diferente
	 * em um grupo com restri��o de curso. 
	 */
	@Test 
	public void testaGrupoComRestricao() {
		try{
			this.controleAluno.alocaAluno("999", "twitch");
			fail("Espera-se uma exce��o.");
		
		}catch(UnsupportedOperationException uoe) {			
		}			
	}
	
	/**
	 * Testa se � lan�ada uma exce��o quando tenta alocar um aluno que n�o existe em um grupo.
	 */
	@Test
	public void testaAlocaAlunoInexistente() {
		try {
		this.controleAluno.cadastraGrupo("Ordem da Fenix", null);
		this.controleAluno.alocaAluno("21", "Ordem da Fenix");
		fail("Espera-se uma exce��o.");
		
		}catch (NoSuchElementException nsex) {		
		}
	}
	

	/**
	 * Testa a verifica��o de restri��o de curso em um grupo. 
	 */
	@Test
	public void testaVerificacaoDeRestricao() {
		this.controleAluno.cadastraGrupo("Fenix", "");
		assertTrue(controleAluno.verificaSeNaoPossuiRestricao("fenix"));
		
		this.controleAluno.cadastraGrupo("Sonserina", "Sangue puro");	
		assertFalse(controleAluno.verificaSeNaoPossuiRestricao("sonserina"));
	}
	
	/**
	 * Testa se um aluno pertence ao grupo. 
	 */
	@Test
	public void testaPertenceAoGrupo() {
		this.controleAluno.cadastraAluno("77", "Ronald Weasley", "Geografia");
		this.controleAluno.alocaAluno("77", "twitch");
		
		assertTrue(this.controleAluno.pertenceAoGrupo("twitch", "77"));
	}
	
	/**
	 * Testa se exce��o � lan�ada quando verifica se o aluno n�o pertence ao grupo.
	 */
	@Test
	public void testaAlunoQueNaoPertenceAoGrupo() {
		try {
		this.controleAluno.cadastraAluno("12", "Gina Weasley", "Arquitetura"); 
		this.controleAluno.cadastraGrupo("Artes das Trevas", "Religi�o");
		this.controleAluno.pertenceAoGrupo("artes das trevas", "12");
		
		fail("Espera-se uma exce��o.");
		}catch(NoSuchElementException nsex) {
		}
	}
	
	/**
	 * Testa se um aluno que respondeu quest�o foi registrado. 
	 */
	@Test
	public void testaRegistraAlunosQueRespoderamQuestao() {
		assertTrue(controleAluno.registraAlunosQueResponderamQuestao("999"));
		
		this.controleAluno.cadastraAluno("43", "Luna Lovegood","Ci�ncias Sociais");
		assertTrue(controleAluno.registraAlunosQueResponderamQuestao("43"));
	}
	
	
	/**
	 * Testa se uma exce��o � lan�ada quando tenta registrar um aluno que n�o existe. 
	 */
	@Test
	public void testaRegistraAlunoQueRespondeuQuestaoNaoCadastrado() {
		try {
			controleAluno.registraAlunosQueResponderamQuestao("7210445");
			fail("Espera-se uma exce��o.");
		}catch(NoSuchElementException nsex) {
		}
	}
	
	/**
	 * Testa se a lista de alunos que respoderam quest�o est� sendo imprimida corretamente. 
	 */
	@Test
	public void imprimeAlunosQueRespoderamQuestao() {
		this.controleAluno.cadastraAluno("610", "Lilian Potter", "Hist�ria");
		this.controleAluno.cadastraAluno("99999", "James Potter", "F�sica");
		controleAluno.registraAlunosQueResponderamQuestao("610");
		controleAluno.registraAlunosQueResponderamQuestao("99999");
		
		String lista = "Alunos: \n" + 
				"1. 610 - Lilian Potter - Hist�ria" + "\n" 
				+ "2. 99999 - James Potter - F�sica" + "\n";
		
		assertEquals(lista, this.controleAluno.imprimeAlunosQueRespoderamQuestao());		
	}
	
	/**
	 * Imprime a contagem de restri��o de curso de um grupo. 
	 */
	@Test
	public void testaContagemDeGruposComRestricaoDeCurso() {
		this.controleAluno.cadastraGrupo("Corvinal", "geeks");
		this.controleAluno.cadastraGrupo("Coruja", "geeks");
		this.controleAluno.cadastraGrupo("Caf�", "Computa��o"); 
		this.controleAluno.cadastraGrupo("Leite", "computa��o"); 
		this.controleAluno.cadastraGrupo("A��car", "Computa��o"); 
		
		assertEquals("geeks 2\n", this.controleAluno.contagemDeGruposComRestricaoDeCurso("geeks"));
		assertEquals("Computa��o 3\n", this.controleAluno.contagemDeGruposComRestricaoDeCurso("Computa��o"));
	}
}
