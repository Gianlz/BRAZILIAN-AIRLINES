package aeroporto;
// Essa classe representa uma fila de aviões em um aeroporto.

public class Fila {
// Declaração de variáveis de instância da classe
	private Aviao primeiro; // Referência para o primeiro avião na fila
	private Aviao ultimo; // Referência para o último avião na fila
	private int tamanho; // Número de aviões na fila
	int pousa = 0;
	int decola = 0;
//Construtor da classe

	public Fila() {
		primeiro = null;
		ultimo = null;
		tamanho = 0;
	}

//Método para adicionar um avião na fila de maneira ordenada pelo combustível
	public void adicionarOrdenado(Aviao aviao) {
		if (primeiro == null || aviao.getCombustivel() > ultimo.getCombustivel()) {
			adicionarp(aviao);
		} else {
			Aviao atual = primeiro;
			Aviao anterior = null;
			while (atual != null && aviao.getCombustivel() <= atual.getCombustivel()) {
				anterior = atual;
				atual = atual.proximo;
			}
			if (anterior == null) {
				aviao.proximo = primeiro;
				primeiro = aviao;
			} else {
				aviao.proximo = atual;
				anterior.proximo = aviao;
			}
			tamanho++;
		}
	}

//Método para adicionar um avião no final da fila
	public void adicionarp(Aviao aviao) {
		if (primeiro == null) {
			primeiro = aviao;
			ultimo = aviao;
		} else {
			ultimo.proximo = aviao;
			ultimo = aviao;
		}
		pousa += +1;
		tamanho++;
	}

	public void adicionard(Aviao aviao) {
		if (primeiro == null) {
			primeiro = aviao;
			ultimo = aviao;
		} else {
			ultimo.proximo = aviao;
			ultimo = aviao;
		}
		decola += +1;
		tamanho++;
	}

//Método para remover o primeiro avião da fila
	public Aviao remover() {
		if (primeiro == null) {
			return null;
		} else {
			Aviao removido = primeiro;
			primeiro = primeiro.proximo;
			tamanho--;
			return removido;
		}
	}

//Método para verificar se a fila está vazia
	public boolean estaVazia() {
		return tamanho == 0;
	}

//Método para obter o tamanho da fila
	public int getTamanho() {
		return tamanho;
	}

//Método para obter o primeiro avião da fila
	public Aviao getPrimeiro() {
		return primeiro;
	}

	public int getPousa() {
		return pousa;
	}

	public void setPousa(int pousa) {
		this.pousa = pousa;
	}

	public int getDecola() {
		return decola;
	}

	public void setDecola(int decola) {
		this.decola = decola;
	}

	// Sobrescrita do método toString() para exibir a fila em formato de string
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Aviao atual = primeiro;
		while (atual != null) {
			sb.append(atual.toString());
			if (atual.proximo != null) {
				sb.append(", ");
			}
			atual = atual.proximo;
		}
		return sb.toString();
	}
}