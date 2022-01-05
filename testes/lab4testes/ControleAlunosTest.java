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
		this.controleAluno.cadastraAluno("74", "Kim", "Física"); 
		this.controleAluno.cadastraAluno("999", "Hermione Granger","Química"); 
		this.controleAluno.cadastraGrupo("Twitch", "geografia");
	}
	
	/**
	 * Testa se o cadastro foi realizado com sucesso. 
	 */
	@Test
	public void testaSeCadastroDeAlunoFoiRealizado() {
		assertTrue(controleAluno.cadastraAluno("43", "Luna Lovegood","Ciências Sociais"));
		assertTrue(controleAluno.cadastraAluno("90", "Neville Longbottom", "Biologia"));
	}
	
	/**
	 * Testa se uma exceção é lançada quando um aluno é cadastrado com a mesma matrícula de um aluno existente.
	 */
	@Test
	public void testaMatriculaJaCadastrada() {
		try {
		this.controleAluno.cadastraAluno("87", "Sirius Black", "Engenharia Elétrica");
		this.controleAluno.cadastraAluno("87", "Bellatrix Lestrange", "Arquitetura");
		fail("Espera-se uma exceção.");
		
		}catch(UnsupportedOperationException uoe){
		}
	}
	
	@Test
	/**
	 * Consulta os dados de um aluno cadastrado. 
	 */
	public void testaConsultaAluno() {	
		this.controleAluno.cadastraAluno("132", "Tom Riddle", "Química");
		assertEquals("Aluno: 132 - Tom Riddle - Química\n", this.controleAluno.consultaAluno("132"));
		
		this.controleAluno.cadastraAluno("99999", "James Potter", "Física");
		assertEquals("Aluno: 99999 - James Potter - Física\n", this.controleAluno.consultaAluno("99999"));
	}
	
	/**
	 * Testa se uma exceção é lançada quando consulta um aluno não cadastrado.
	 */
	@Test
	public void testaConsultaAlunoInexistente() {
		try {
			this.controleAluno.consultaAluno("72");
			this.controleAluno.consultaAluno("01");
			fail("Espera-se uma exceção.");
		}catch (NoSuchElementException nsex){			
		}
	}
	
	/**
	 * Testa se um grupo é cadastrado com sucesso.
	 */
	@Test
	public void testaCadastraGrupo() {
		assertTrue(controleAluno.cadastraGrupo("Lufa lufa", null)); 
		assertTrue(controleAluno.cadastraGrupo("Sonserina", "Sangue puro"));		
	}
	
	/**
	 * Testa se uma exceção é lançada quando tenta cadastrar um grupo com um nome que já foi cadastrado.
	 */
	@Test
	public void testaGrupoJaCadastrado() {
		try {
			this.controleAluno.cadastraGrupo("laranja", "Matemática"); 
			this.controleAluno.cadastraGrupo("laranja", "");
			fail("Espera-se uma exceção.");
		
		}catch(UnsupportedOperationException uoe) {			
		}		
	}
	
	/**
	 * Testa se um aluno é alocado em um grupo. 
	 */
	@Test
	public void testaAlocaAluno() {
		this.controleAluno.cadastraAluno("77", "Ronald Weasley", "Geografia");
		assertTrue(this.controleAluno.alocaAluno("77", "twitch"));
	}
	
	/**
	 * Testa se uma exceção é lançada quando tenta alocar um aluno de um curso diferente
	 * em um grupo com restrição de curso. 
	 */
	@Test 
	public void testaGrupoComRestricao() {
		try{
			this.controleAluno.alocaAluno("999", "twitch");
			fail("Espera-se uma exceção.");
		
		}catch(UnsupportedOperationException uoe) {			
		}			
	}
	
	/**
	 * Testa se é lançada uma exceção quando tenta alocar um aluno que não existe em um grupo.
	 */
	@Test
	public void testaAlocaAlunoInexistente() {
		try {
		this.controleAluno.cadastraGrupo("Ordem da Fenix", null);
		this.controleAluno.alocaAluno("21", "Ordem da Fenix");
		fail("Espera-se uma exceção.");
		
		}catch (NoSuchElementException nsex) {		
		}
	}
	

	/**
	 * Testa a verificação de restrição de curso em um grupo. 
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
	 * Testa se exceção é lançada quando verifica se o aluno não pertence ao grupo.
	 */
	@Test
	public void testaAlunoQueNaoPertenceAoGrupo() {
		try {
		this.controleAluno.cadastraAluno("12", "Gina Weasley", "Arquitetura"); 
		this.controleAluno.cadastraGrupo("Artes das Trevas", "Religião");
		this.controleAluno.pertenceAoGrupo("artes das trevas", "12");
		
		fail("Espera-se uma exceção.");
		}catch(NoSuchElementException nsex) {
		}
	}
	
	/**
	 * Testa se um aluno que respondeu questão foi registrado. 
	 */
	@Test
	public void testaRegistraAlunosQueRespoderamQuestao() {
		assertTrue(controleAluno.registraAlunosQueResponderamQuestao("999"));
		
		this.controleAluno.cadastraAluno("43", "Luna Lovegood","Ciências Sociais");
		assertTrue(controleAluno.registraAlunosQueResponderamQuestao("43"));
	}
	
	
	/**
	 * Testa se uma exceção é lançada quando tenta registrar um aluno que não existe. 
	 */
	@Test
	public void testaRegistraAlunoQueRespondeuQuestaoNaoCadastrado() {
		try {
			controleAluno.registraAlunosQueResponderamQuestao("7210445");
			fail("Espera-se uma exceção.");
		}catch(NoSuchElementException nsex) {
		}
	}
	
	/**
	 * Testa se a lista de alunos que respoderam questão está sendo imprimida corretamente. 
	 */
	@Test
	public void imprimeAlunosQueRespoderamQuestao() {
		this.controleAluno.cadastraAluno("610", "Lilian Potter", "História");
		this.controleAluno.cadastraAluno("99999", "James Potter", "Física");
		controleAluno.registraAlunosQueResponderamQuestao("610");
		controleAluno.registraAlunosQueResponderamQuestao("99999");
		
		String lista = "Alunos: \n" + 
				"1. 610 - Lilian Potter - História" + "\n" 
				+ "2. 99999 - James Potter - Física" + "\n";
		
		assertEquals(lista, this.controleAluno.imprimeAlunosQueRespoderamQuestao());		
	}
	
	/**
	 * Imprime a contagem de restrição de curso de um grupo. 
	 */
	@Test
	public void testaContagemDeGruposComRestricaoDeCurso() {
		this.controleAluno.cadastraGrupo("Corvinal", "geeks");
		this.controleAluno.cadastraGrupo("Coruja", "geeks");
		this.controleAluno.cadastraGrupo("Café", "Computação"); 
		this.controleAluno.cadastraGrupo("Leite", "computação"); 
		this.controleAluno.cadastraGrupo("Açúcar", "Computação"); 
		
		assertEquals("geeks 2\n", this.controleAluno.contagemDeGruposComRestricaoDeCurso("geeks"));
		assertEquals("Computação 3\n", this.controleAluno.contagemDeGruposComRestricaoDeCurso("Computação"));
	}
}
