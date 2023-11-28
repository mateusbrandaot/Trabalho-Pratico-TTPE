package ttpe.trabalho.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ttpe.trabalho.exception.ValorInvalidoException;

public class Estoque {

    private Map<String, Produto> produtos = new HashMap<>();
   
    private List<Transacao> transacoes = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public Produto getProduto(String produtoId) {
        return produtos.get(produtoId);
    }
   
    public List<Transacao> getTransacoes() {
        return transacoes;
    }
    
    public void registrarTransacao(String idProduto, int quantidade, String tipoTransacao,  Empresa empresaOrigem, Empresa empresaDestino) throws Exception {
    	Produto produto = produtos.get(idProduto);  
    	Date now = new Date();  
    	Transacao transacao = new Transacao(produto, quantidade, now, tipoTransacao);
        if(transacao.getQuantidade() < 0 ) {
        	 throw new ValorInvalidoException("quantidade transacao inválida.");
        }
    	transacoes.add(transacao);
       
        switch (tipoTransacao) {
        case "Venda":
        	transacaoVendaProduto(idProduto, quantidade);
        	break;
        case "Recebimento":
        	transacaoRecebimentoProduto(idProduto, quantidade);
            break;
        case "Devolução":
        	transacaoDevolucaooProduto(idProduto, quantidade);
            break;
        case "Transferencia":
        	transacaoTransferenciaProduto(idProduto, empresaOrigem, empresaDestino, quantidade);
            break;
        default:
           
        	System.out.println("Tipo de transação não reconhecido: " + tipoTransacao);
            break;
        }
     produto.alertaEstoque(now);   
    }    
   

    public void transacaoVendaProduto(String produtoId, int quantidade) {
    	Produto produto = produtos.get(produtoId);   		 
    	produto.removerEstoque(quantidade);
    }
    
    public void transacaoRecebimentoProduto(String produtoId, int quantidade) {
    	Produto produto = produtos.get(produtoId);  
    	produto.adicionarEstoque(quantidade);
    }
    
    public void  transacaoDevolucaooProduto(String produtoId, int quantidade) {
    	Produto produto = produtos.get(produtoId);  
    	produto.adicionarEstoque(quantidade);
    }
    
   
    public void transacaoTransferenciaProduto(String produtoId, Empresa empresaOrigem, Empresa empresaDestino, int quantidade) throws Exception {
        Produto produto = produtos.get(produtoId);

        if (produto == null) {
            throw new Exception("Produto não encontrado.");
        }

        if (produto.getEmpresaDetentora().getId() != empresaOrigem.getId()) {
            throw new Exception("O produto não pertence à empresa de origem.");
        }

        if (produto.getQuantidadeEmEstoque() < quantidade) {
            throw new Exception("Quantidade em estoque insuficiente para transferência.");
        }
        
        
        produto.setEmpresaDetentora(empresaDestino);

    }

    
    public int produtoEmEstoque (String idproduto) {
    	Produto produto = produtos.get(idproduto);
    	int qtd = produto.getQuantidadeEmEstoque();
    	return qtd;
    }
    public Double calculaValorEmEstoqueProduto (String idproduto) {
    	Produto produto = produtos.get(idproduto);
    	Double valor = produto.getQuantidadeEmEstoque() * produto.getPreco();
    	return valor;
    }
   
    public int quantidadeProdutoEstoque (String idproduto) {
    	Produto produto = produtos.get(idproduto);
    	int valor = produto.getQuantidadeEmEstoque();
    	return valor;
    }
    
    public Produto getProdutoNome(String nome) {
    	Optional<Produto> produtoEncontrado = produtos.values().stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst();

        if(produtoEncontrado.isPresent()) {
        	return produtoEncontrado.get();
        } else return null;

    }
    
    public Produto getProdutoCodigoBarra(String codigoBarra) {
    	Optional<Produto> produtoEncontrado = produtos.values().stream()
                .filter(p -> p.getCodigoBarra().equals(codigoBarra))
                .findFirst();

        if(produtoEncontrado.isPresent()) {
        	return produtoEncontrado.get();
        } else return null;

    }
}

