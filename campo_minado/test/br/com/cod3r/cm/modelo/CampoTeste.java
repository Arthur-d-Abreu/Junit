package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo(){
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
			Campo vizinho = new Campo(3,2);
			boolean resultado = campo.adicionarVizinho(vizinho);
			assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Direita() {
			Campo vizinho= new Campo(3,4);
			boolean resultado = campo.adicionarVizinho(vizinho);
			assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1EmCima() {
			Campo vizinho = new Campo(2,3);
			boolean resultado = campo.adicionarVizinho(vizinho);
			assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Embaixo() {
			Campo vizinho = new Campo(4,3);
			boolean resultado = campo.adicionarVizinho(vizinho);
			assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDiagonalDireitaCima() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDiagonalDireitaBaixo() {
		Campo vizinho = new Campo(4,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDiagonalEsquerdaCima() {
		Campo vizinho = new Campo(2,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDiagonalEsquerdaBaixo() {
		Campo vizinho = new Campo(4,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
		campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos1() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.adicionarVizinho(campo11);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.adicionarVizinho(campo11);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void testeObjetivoAlcancadoNaoMinado() {
		Campo campo11 = new Campo(1,1);
		
		campo11.abrir();
		
		assertTrue(campo11.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancadoMinadoMarcado() {
		Campo campo11 = new Campo(1,1);
		campo11.minar();
		campo11.alternarMarcacao();
		campo11.abrir();
		
		assertTrue(campo11.objetivoAlcancado());
	}
	
	@Test
	void testeLinha() {
		Campo campo14 = new Campo(1, 4);
		
		int campoEsperado = 1;
		int camporetornado = campo14.getLinha();
		
		assertEquals(campoEsperado, camporetornado);
	}
	
	@Test
	void testeColuna() {
		Campo campo14 = new Campo(1, 4);
		
		int campoEsperado = 4;
		int camporetornado = campo14.getColuna();
		
		assertEquals(campoEsperado, camporetornado);
	}
	
	@Test 
	void testeReiniciar() {
		Campo campo11 = new Campo(1,1);
		
		campo11.abrir();
		campo11.minar();
		campo11.alternarMarcacao();
		campo11.reiniciar();
		
		assertFalse(campo11.isAberto() && campo11.isMinado() && campo11.isMarcado());
	}
	
	@Test
	void testeStringMarcado() {
		Campo campo11 = new Campo(1,1);
		
		campo11.alternarMarcacao();
		
		String esperado = "x";
		String retornado = campo11.toString();
		
		assertEquals(esperado, retornado);
	}
	
	@Test
	void testeStringAbertoMinado() {
		Campo campo11 = new Campo(1,1);
		
		campo11.abrir();
		campo11.minar();
		
		String esperado = "*";
		String retornado = campo11.toString();
		
		assertEquals(esperado, retornado);
	}
	
	
}
