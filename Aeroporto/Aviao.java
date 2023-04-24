package aeroporto;

public class Aviao {
	private int id;
	private int combustivel;
	private boolean paraAterrissagem;
	private int tempo;
	public Aviao proximo;

	public Aviao(int id, int combustivel, boolean paraAterrissagem) {
		// Construtor da classe Aviao
		this.id = id;
		this.combustivel = combustivel;
		this.paraAterrissagem = paraAterrissagem;
		this.tempo = 0;

	}

	// Métodos getters e setters para acessar as propriedades privadas da classe
	public int getId() {
		return id;
	}

	public int getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(int combustivel) {
		this.combustivel = combustivel;
	}

	public boolean isParaAterrissagem() {
		return paraAterrissagem;
	}

	public void setParaAterrissagem(boolean paraAterrissagem) {
		this.paraAterrissagem = paraAterrissagem;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	// Métodos para atualizar as propriedades do avião
	public void decrementaCombustivel() {
		combustivel--;
	}

	// Método toString para exibir informações do avião
	@Override
	public String toString() {
		return "Aviao #" + id;// + " Combusítvel: " + getCombustivel();
	}
}
