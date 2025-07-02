public class Sala {
    String valor, descricao, tipo;
    int id;
    Sala anterior, proximo;

    public Sala(String valor, String descricao, String tipo, int id) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        this.id = id;
        this.anterior = null;
        this.proximo = null;
    }
}
