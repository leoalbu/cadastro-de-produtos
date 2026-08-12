public class Produto {
    private String nome;
    private Double preco;
    private String cor;
    private String descricao;
    private Integer estoque;

    @Override
    public String toString() {
        return "Nome: " + this.nome + " Preço: " + this.preco + " Cor: " +
                this.cor + "Descrição:   " + this.descricao + "Estoque: " + this.estoque;

    }

    public Produto(String nome, Double preco, String cor, String descricao, Integer estoque) {
        this.nome = nome;
        this.preco = preco;
        this.cor = cor;
        this.descricao = descricao;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
