package aeroporto;

import java.util.Random;
import java.util.Scanner;

public class Aeroporto {

	private Fila pista1Aterrissagem;
	private Fila pista2Aterrissagem;
	private Fila pista1Decolagem;
	private Fila pista2Decolagem;
	int reserva;
	private int tempo;
	private int tempoEsperaTotalDecolagem = 0;
	private int numAvioesDecolagem = 0;
	private int idDecolagem = 0;
	private int idAterrissagem = -1;

	private Random random;

	int decola;
	int pousa;

	// Construtor da classe Aeroporto
	public Aeroporto() {
		// Inicializa as filas de aviões para aterrissagem e decolagem, e outras
		// variáveis de controle
		this.pista1Aterrissagem = new Fila();
		this.pista2Aterrissagem = new Fila();
		this.pista1Decolagem = new Fila();
		this.pista2Decolagem = new Fila();
		this.tempo = 0;
		this.random = new Random();
	}

	// Método para gerar o ID para decolagem de um avião
	public int GeraIDD() {
		idDecolagem += 2;
		return idDecolagem;
	}

	// Método para gerar o ID para aterrissagem de um avião
	public int GeraIDA() {
		idAterrissagem += 2;
		return idAterrissagem;
	}

	// Método para gerar a quantidade de combustível de um avião
	public int GeraComb() {
		int num = random.nextInt(1, 20);
		return num;
	}

	public String tempoMedioPorCombustivel() {
		for (int i = 0; i < 20; i++) {
			int totalTempo = 0;
			int totalAvioes = 0;
			Aviao val = pista1Aterrissagem.getPrimeiro();

			while (val != null) {
				if (val.getCombustivel() == i) {
					totalTempo += val.getTempo();
					totalAvioes++;
				}
				val = val.proximo;
			}

			val = pista2Aterrissagem.getPrimeiro();

			while (val != null) {
				if (val.getCombustivel() == i) {
					totalTempo += val.getTempo();
					totalAvioes++;
				}
				val = val.proximo;
			}

			if (totalAvioes > 0) {
				double mediaTempo = (double) totalTempo / totalAvioes;
				return "\u001B[32mMédia de tempo de aterrissagem: " + mediaTempo + "\nMédia de tempo de Decolagem: "
						+ (mediaTempo + 1.0) + "\u001B[0m";
			}

		}
		return "\u001B[32mMédia de tempo de aterrissagem: 0.0\nMédia de tempo de Decolagem: 0.0\u001B[0m";
	}

	// Método para adicionar um avião na fila correspondente
	public void adicionarAviao(Aviao aviao) {
		if (aviao.isParaAterrissagem()) {
			if (aviao.getCombustivel() <= 5) { // verifica se o combustível está perto de 1
				// pousa imediatamente
				pista1Aterrissagem.adicionarp(aviao);
			} else if (pista1Aterrissagem.estaVazia()) {
				pista1Aterrissagem.adicionarp(aviao);
			} else if (pista2Aterrissagem.estaVazia()) {
				pista2Aterrissagem.adicionarp(aviao);
			} else {
				pista1Aterrissagem.adicionarOrdenado(aviao);
			}
			pousa += +1;
		} else {
			if (pista1Decolagem.estaVazia()) {
				pista1Decolagem.adicionard(aviao);
			} else if (pista2Decolagem.estaVazia()) {
				pista2Decolagem.adicionard(aviao);
			} else {
				pista1Decolagem.adicionarOrdenado(aviao);
			}
			decola += +1;
		}
	}

	public void imprimir() {
		pista1Aterrissagem.toString();
	}
	// Aterrissagem e decolagem

	// Método que executa a simulação
	public void executar() {
		Scanner scanner = new Scanner(System.in);
		int numAvioesAterrissagem, numAvioesDecolagem;
		while (true) {

			// Pergunta ao usuário se deseja avançar um tempo ou encerrar a simulação
			System.out.println("Pressione enter para avançar um tempo ou digite 'fim' para encerrar.");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("fim")) {
				break;
			}

			tempo++;

			// Chegada de aviões
			numAvioesAterrissagem = random.nextInt(3);
			for (int i = 0; i < numAvioesAterrissagem; i++) {
				if (pista1Aterrissagem.getTamanho() <= pista2Aterrissagem.getTamanho()) {
					pista1Aterrissagem.adicionarp(new Aviao(GeraIDA(), GeraComb(), true));
				} else {
					pista2Aterrissagem.adicionarp(new Aviao(GeraIDA(), GeraComb(), true));
				}
			}

			numAvioesDecolagem = random.nextInt(3);
			for (int i = 0; i < numAvioesDecolagem; i++) {
				if (pista1Decolagem.getTamanho() <= pista2Decolagem.getTamanho()) {
					pista1Decolagem.adicionard(new Aviao(GeraIDD(), 20, false));
				} else {
					pista2Decolagem.adicionard(new Aviao(GeraIDD(), 20, false));
				}
			}

			// Imprime informações das filas de espera
			System.out.println("\u001B[36m===== FILAS DE AVIÕES =====\u001B[0m");
			System.out.println("\u001B[36mFila pista 1 para decolagem:\n" + pista1Decolagem.toString() + "\n\u001B[0m");
			System.out.println("\u001B[36mFila pista 2 para decolagem:\n" + pista2Decolagem.toString() + "\n\u001B[0m");
			System.out.println(
					"\u001B[36mFila pista 1 para aterrissagem:\n" + pista1Aterrissagem.toString() + "\n\u001B[0m");
			System.out.println(
					"\u001B[36mFila pista 2 para aterrissagem:\n" + pista2Aterrissagem.toString() + "\n\u001B[0m");
			System.out.println(tempoMedioPorCombustivel());
			System.out.println("\n\u001B[31mAviões em emergência: \u001B[0m" + reserva + "\n");

			Aviao val = pista1Aterrissagem.getPrimeiro();

			while (val != null) {
				val.setTempo(val.getTempo() + 1);
				val.setCombustivel(val.getCombustivel() - 1);
				val = val.proximo;
			}
			val = pista2Aterrissagem.getPrimeiro();

			while (val != null) {
				val.setTempo(val.getTempo() + 1);
				val.setCombustivel(val.getCombustivel() - 1);
				val = val.proximo;
			}
			val = pista1Decolagem.getPrimeiro();

			while (val != null) {
				val.setTempo(val.getTempo() + 1);
				val = val.proximo;
			}
			val = pista2Decolagem.getPrimeiro();

			while (val != null) {
				val.setTempo(val.getTempo() + 1);
				val = val.proximo;
			}

			// Decolagem
			if (!pista1Decolagem.estaVazia()) {
				Aviao aviao = pista1Decolagem.remover();
				System.out.println("Avião #" + aviao.getId() + " decolou da pista 1 de decolagem.");
			} else if (!pista2Decolagem.estaVazia()) {
				Aviao aviao = pista2Decolagem.remover();
				System.out.println("Avião #" + aviao.getId() + " decolou da pista 2 de decolagem.");
			}

			// Aterrissagem

			if (!pista1Aterrissagem.estaVazia()) {
				Aviao aviao = pista1Aterrissagem.getPrimeiro();
				if (aviao.getCombustivel() <= 5 || aviao.isParaAterrissagem()) {

					pista1Aterrissagem.remover();
					if (aviao.getCombustivel() <= 5) {
						reserva++;
						System.out.println(
								"Avião #" + aviao.getId() + " aterrissou de emergência na pista 1 de aterrissagem.");
					} else {
						System.out.println("Avião #" + aviao.getId() + " aterrissou na pista 1 de aterrissagem.");
					}
				} else {
					aviao.setCombustivel(aviao.getCombustivel() - 1);
					System.out.println("Avião #" + aviao.getId()
							+ " na pista 1 de aterrissagem aguardando para aterrissar (combustível restante: "
							+ aviao.getCombustivel() + ").");
				}
			} else if (!pista2Aterrissagem.estaVazia()) {
				Aviao aviao = pista2Aterrissagem.getPrimeiro();
				if (aviao.getCombustivel() <= 5 || aviao.isParaAterrissagem()) {
					pista2Aterrissagem.remover();
					if (aviao.getCombustivel() <= 5) {
						reserva++;
						System.out.println(
								"Avião #" + aviao.getId() + " aterrissou de emergência na pista 2 de aterrissagem.");
					} else {
						System.out.println("Avião #" + aviao.getId() + " aterrissou na pista 2 de aterrissagem.");
					}
				} else {
					aviao.setCombustivel(aviao.getCombustivel() - 1);
					System.out.println("Avião #" + aviao.getId()
							+ " na pista 2 de aterrissagem aguardando para aterrissar (combustível restante: "
							+ aviao.getCombustivel() + ").");
				}
			}
			// Verifica se alguma aeronave está em estado de emergência
			if (!pista1Aterrissagem.estaVazia()) {
				Aviao aviao = pista1Aterrissagem.getPrimeiro();
				if (aviao.getCombustivel() <= 5) {
					aviao.setParaAterrissagem(true);
				}
			}
			if (!pista2Aterrissagem.estaVazia()) {
				Aviao aviao = pista2Aterrissagem.getPrimeiro();
				if (aviao.getCombustivel() <= 5) {
					aviao.setParaAterrissagem(true);
				}
			}

		}

		scanner.close();
	}

}