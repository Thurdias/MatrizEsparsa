package MatrizEsparsa;

import org.w3c.dom.Node;

public class MatrizEsparsa {
	/// Constantes da classe
	private final int MODULO = 7;

	// Propriedades da classe
	private NoDiretor cabeca = null;

	// M�todos da classe
	public void inserir(int numero) {
		// Declara��o de vari�veis
		NoDiretor ponteiro = cabeca;
		
		// Navega��o nos n�s diretores procurando o n�mero correto
		while(ponteiro != null) {
			if(ponteiro.getNumero() == (numero % MODULO)) {
				break;
			}
			ponteiro = ponteiro.getProximoDiretor();
		}
		
		// Situa��o em que � necess�rio criar um novo n� diretor
		if(ponteiro==null) {
			ponteiro = new NoDiretor((numero % MODULO), null, null);
			if(cabeca == null) {
				cabeca = ponteiro;
		} else {
			ponteiro.setProximoDiretor(cabeca);
			cabeca = ponteiro;
			}			
		}
		
		// Situa��o em que o n� diretor j� foi encontrado pelo ponteiro
		No novoNo = new No(numero, ponteiro.getProximoNo());
		ponteiro.setProximoNo(novoNo);
	}
		
	public void mostrar() {
		NoDiretor ponteiroDiretor = cabeca;
		
		System.out.println("M�dulo\tN�meros");
		while (ponteiroDiretor != null) {
			No ponteiroNo = ponteiroDiretor.getProximoNo();
			
			System.out.print(ponteiroDiretor.getNumero() + "\t");
			
			while (ponteiroNo != null) {
				System.out.print(ponteiroNo.getNumero() + "\t");
				ponteiroNo = ponteiroNo.getProximo();
			}
			
			System.out.println();
			ponteiroDiretor = ponteiroDiretor.getProximoDiretor();
		}
	}
	
	public boolean procurar(int numero) {
		NoDiretor ponteiroDiretor = cabeca;
		boolean achei = false;
		
		while(ponteiroDiretor != null) {
			if(ponteiroDiretor.getNumero() == (numero % MODULO)) {
				No ponteiroNo = ponteiroDiretor.getProximoNo();
				
				while(ponteiroNo != null) {
					if (ponteiroNo.getNumero() == numero) {
						achei = true;
						break;
					}
					ponteiroNo = ponteiroNo.getProximo();
				}
			}
			ponteiroDiretor = ponteiroDiretor.getProximoDiretor();
		}
		return achei;
	}
	
	public void remover(int numero) {
		NoDiretor ponteiroDiretor = cabeca;
		
		while(ponteiroDiretor != null) {
			if(ponteiroDiretor.getNumero() == (numero% MODULO)) {
				No ponteiroNo = ponteiroDiretor.getProximoNo();
				
				if(ponteiroNo != null) {
					if(ponteiroNo.getNumero() == numero) {
						ponteiroDiretor.setProximoNo(ponteiroNo.getProximo());
					} else {
						while((ponteiroNo.getProximo() != null) && (ponteiroNo != null)) {
							if(ponteiroNo.getProximo().getNumero() == numero) {
								ponteiroNo.setProximo(ponteiroNo.getProximo().getProximo());
							}
							
							ponteiroNo = ponteiroNo.getProximo();
						}
					}
				}
			}
			
			ponteiroDiretor = ponteiroDiretor.getProximoDiretor();
		}
	}
}
