public class Labirinto {
    Sala inicio, fim;

    public Labirinto() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionarSala(String valor, String descricao, String tipo, int id) {
        Sala novaSala = new Sala(valor, descricao, tipo, id);

        if (inicio == null) {
            inicio = novaSala;
            fim = novaSala;
        } else {
            fim.proximo = novaSala;
            novaSala.anterior = fim;
            fim = novaSala;
        }
    }

    public void removerSala(String valor, String descricao, String tipo, int id) {
        if (inicio == null) {
            System.out.println("Não há salas para remover.");
            return;
        }

        Sala atual = inicio;

        while (atual != null) {
            if (atual.id == id) {
                if (atual == inicio && atual == fim) {
                    inicio = null;
                    fim = null;
                } else if (atual == inicio) {
                    inicio = atual.proximo;
                    if (inicio != null) inicio.anterior = null;
                } else if (atual == fim) {
                    fim = atual.anterior;
                    if (fim != null) fim.proximo = null;
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }

                System.out.println("Sala com o ID " + id + " removida.");
                return;
            }

            atual = atual.proximo;
        }

        System.out.println("Sala com o ID " + id + " não encontrada.");
    }

    public void iniciarLabirintoPara(Jogador jogador) {
        if (contarSalas() < 10) {
            System.out.println("Você precisa cadastrar pelo menos 10 salas antes de iniciar o jogo.");
            System.exit(0);
        }
        jogador.salaAtual = inicio;
        jogador.exibirSalaAtual();
    }

    public int contarSalas() {
        int contador = 0;
        Sala atual = inicio;
        while (atual != null) {
            contador++;
            atual = atual.proximo;
        }
        return contador;
    }
}
