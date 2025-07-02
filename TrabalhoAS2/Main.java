import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();

        // Cadastro de pelo menos 10 salas (9 normais + 1 saída)
        labirinto.adicionarSala("Nada aqui", "Você entrou na primeira sala.", "normal", 1);
        labirinto.adicionarSala("", "Sala com cheiro estranho...", "armadilha", 2);
        labirinto.adicionarSala("Poção de Cura", "Uma luz brilha no canto.", "prêmio", 3);
        labirinto.adicionarSala("", "Piso escorregadio e úmido.", "normal", 4);
        labirinto.adicionarSala("Chave Enferrujada", "Você encontra algo escondido.", "prêmio", 5);
        labirinto.adicionarSala("", "Paredes começam a se mover!", "armadilha", 6);
        labirinto.adicionarSala("", "A sala está vazia e fria.", "normal", 7);
        labirinto.adicionarSala("Escudo Antigo", "Algo reluz no canto.", "prêmio", 8);
        labirinto.adicionarSala("", "Você ouve barulhos estranhos.", "normal", 9);
        labirinto.adicionarSala("", "Porta com runas brilhantes.", "saída", 10);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine();
        Jogador jogador = new Jogador(nome);
        labirinto.iniciarLabirintoPara(jogador);

        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Ir para próxima sala");
            System.out.println("2 - Voltar para sala anterior");
            System.out.println("3 - Interagir com a sala");
            System.out.println("4 - Ver inventário e pontuação");
            System.out.println("0 - Sair do jogo");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> jogador.moverParaProximaSala();
                case 2 -> jogador.moverParaSalaAnterior();
                case 3 -> jogador.interagirComSala();
                case 4 -> {
                    System.out.println("Inventário: " + jogador.inventario);
                    System.out.println("Pontuação: " + jogador.pontuacao);
                }
                case 0 -> System.out.println("Saindo do labirinto...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
