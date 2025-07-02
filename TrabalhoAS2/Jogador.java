import java.util.ArrayList;

public class Jogador {
    String nome;
    int pontuacao;
    ArrayList<String> inventario;
    Sala salaAtual;
    ArrayList<Integer> salasVisitadas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.inventario = new ArrayList<>();
        this.salasVisitadas = new ArrayList<>();
    }

    public void moverParaProximaSala() {
        if (salaAtual != null && salaAtual.proximo != null) {
            salaAtual = salaAtual.proximo;
            System.out.println("Você se moveu para a próxima sala.");
            exibirSalaAtual();
        } else {
            System.out.println("Você já está na última sala.");
        }
    }

    public void moverParaSalaAnterior() {
        if (salaAtual != null && salaAtual.anterior != null) {
            salaAtual = salaAtual.anterior;
            System.out.println("Você voltou para a sala anterior.");
            exibirSalaAtual();
        } else {
            System.out.println("Você já está na primeira sala.");
        }
    }

    public void interagirComSala() {
        if (salaAtual == null) return;

        if (!salasVisitadas.contains(salaAtual.id) && !salaAtual.tipo.equalsIgnoreCase("saída")) {
            salasVisitadas.add(salaAtual.id);
        }

        switch (salaAtual.tipo.toLowerCase()) {
            case "armadilha" -> {
                System.out.println("Você encontrou uma armadilha!");
                pontuacao -= 10;
                moverParaSalaAnterior();
            }
            case "prêmio" -> {
                System.out.println("Parabéns! Você encontrou um item raro.");
                if (!salaAtual.valor.isEmpty()) {
                    inventario.add(salaAtual.valor);
                }
                pontuacao += 20;
            }
            case "saída" -> {
                if (visitouTodasAsSalas()) {
                    System.out.println("Você venceu o jogo!");
                    exibirResumoFinal();
                    System.exit(0);
                } else {
                    System.out.println("Você chegou à saída, mas ainda não explorou tudo!");
                }
            }
            default -> {
                System.out.println("Nada de especial aqui...");
            }
        }
    }

    public void exibirSalaAtual() {
        if (salaAtual != null) {
            System.out.println("Sala atual: " + salaAtual.descricao + " (Tipo: " + salaAtual.tipo + ")");
        }
    }

    private boolean visitouTodasAsSalas() {
        Sala atual = salaAtual;
        while (atual.anterior != null) {
            atual = atual.anterior;
        }
        int total = 0;
        while (atual != null) {
            if (!atual.tipo.equalsIgnoreCase("saída")) {
                total++;
            }
            atual = atual.proximo;
        }
        return salasVisitadas.size() == total;
    }

    public void exibirResumoFinal() {
        System.out.println("--- FIM DO JOGO ---");
        System.out.println("Pontuação final: " + pontuacao);
        System.out.println("Inventário: " + inventario);
        System.out.println("Salas visitadas: " + salasVisitadas.size());
    }
}
