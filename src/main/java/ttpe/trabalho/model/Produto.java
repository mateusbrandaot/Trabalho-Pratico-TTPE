package ttpe.trabalho.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import ttpe.trabalho.exception.DescricaoEmBrancoException;
import ttpe.trabalho.exception.EstoqueNegativoException;
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
	
	private int QtdMinimaEstoque;
	
	private Date validade ;
	
  
	public Produto(String id, String nome, String descricao, String codigoBarra, 
	             double preco, int quantidadeEmEstoque, 
	             Empresa empresaDetentora, Fornecedor fornecedor, int QtdMinimaEstoque, Date validade) 
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
	  this.QtdMinimaEstoque = QtdMinimaEstoque;
	  this.validade = validade;
	}
    
	
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCodigoBarra() {
		return codigoBarra;
	}


	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}


	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}


	public Empresa getEmpresaDetentora() {
		return empresaDetentora;
	}


	public void setEmpresaDetentora(Empresa empresaDetentora) {
		this.empresaDetentora = empresaDetentora;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	

	public int getQtdMinimaEstoque() {
		return QtdMinimaEstoque;
	}


	public void setQtdMinimaEstoque(int qtdMinimaEstoque) {
		QtdMinimaEstoque = qtdMinimaEstoque;
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
    
    public void alertaEstoque(Date data) throws EstoqueNegativoException {
    	if(quantidadeEmEstoque < QtdMinimaEstoque) {
    		System.out.printf("estoque baixo produto %s total %s fornecedor %s ", this.id, this.quantidadeEmEstoque, this.fornecedor.getNome());
    	}
    	if(isProximoVencimento(data)) {
    		System.out.print("produtos próximos de vencer");
    		
    	Double valorDesconto = this.preco * 0.2;
    	Double valorFinal = this.preco - valorDesconto;
    	this.preco = valorFinal;
    	}
		if(this.quantidadeEmEstoque < 0 ) {
			this.quantidadeEmEstoque = 0;
			throw new EstoqueNegativoException("Estoque negativo");
		}
	    	
    }
    
    public  boolean isProximoVencimento(Date date1) {
    	// Calcula a diferença em milissegundos entre as duas datas
        long diffInMillis = Math.abs(date1.getTime() - this.validade.getTime());
        // Converte a diferença em milissegundos para dias
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        // Retorna true se a diferença for exatamente 10 dias
        return diffInDays == 10;
    }
}
