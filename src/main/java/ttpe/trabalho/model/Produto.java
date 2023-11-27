package ttpe.trabalho.model;

import ttpe.trabalho.exception.DescricaoEmBrancoException;
import ttpe.trabalho.exception.ValorInvalidoException;

public class Produto {
    
	private String id;
    
	private String nome;
    
	private String descricao;
	
	private String codigoBarra;
    
	private double preco;
    
	private int quantidadeEmEstoque;
	
	private Empresa empresaDetentora;

	private Fornecedor fornecedor;
	
  
	public Produto(String id, String nome, String descricao, String codigoBarra, 
	             double preco, int quantidadeEmEstoque, 
	             Empresa empresaDetentora, Fornecedor fornecedor) 
	             throws DescricaoEmBrancoException, ValorInvalidoException {
	  if (nome == null || nome.trim().isEmpty() ||
	      codigoBarra == null || codigoBarra.trim().isEmpty() ||
	      descricao == null || descricao.trim().isEmpty()) {
	      throw new DescricaoEmBrancoException("Nome, código de barras ou descrição não podem estar em branco.");
	  }
	  
	  if (preco <= 0 || quantidadeEmEstoque <= 0) {
	      throw new ValorInvalidoException("Preço e quantidade em estoque devem ser maiores que zero.");
	  }

	  this.id = id;
	  this.nome = nome;
	  this.descricao = descricao;
	  this.codigoBarra = codigoBarra;
	  this.preco = preco;
	  this.quantidadeEmEstoque = quantidadeEmEstoque;
	  this.empresaDetentora = empresaDetentora;
	  this.fornecedor = fornecedor;
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
