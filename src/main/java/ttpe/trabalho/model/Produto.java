package ttpe.trabalho.model;

public class Produto {
    
	private String id;
    
	private String nome;
    
	private String descricao;
    
	private double preco;
    
	private int quantidadeEmEstoque;
	
	private Empresa empresaDetentora;

    public Produto(String id, String nome, String descricao, double preco, int quantidadeEmEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Método para adicionar estoque
    public void adicionarEstoque(int quantidade) {
        this.quantidadeEmEstoque += quantidade;
    }

    // Método para remover estoque
    public boolean removerEstoque(int quantidade) {
        if (quantidade <= quantidadeEmEstoque) {
            quantidadeEmEstoque -= quantidade;
            return true;
        } else {
            return false;
        }
    }
}
