package ttpe.trabalho.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import ttpe.trabalho.exception.EstoqueNegativoException;
import ttpe.trabalho.exception.ValorInvalidoException;
import ttpe.trabalho.model.Empresa;
import ttpe.trabalho.model.Estoque;
import ttpe.trabalho.model.Produto;
import ttpe.trabalho.model.Transacao;

public class GerenciadorEstoqueService {
	
	private Estoque estoque;

	public void registrarTransacao(String idProduto, int quantidade, String tipoTransacao, Empresa empresaOrigem,
			Empresa empresaDestino) throws Exception {
		Produto produto = estoque.getProdutoPorId(idProduto);
		Date now = new Date();
		Transacao transacao = new Transacao(produto, quantidade, now, tipoTransacao);
		if (transacao.getQuantidade() < 0) {
			throw new ValorInvalidoException("quantidade transacao inválida.");
		}
		
		estoque.adicionarTransacao(transacao);

		switch (tipoTransacao) {
		case "Venda":
			transacaoVendaProduto(produto, quantidade);
			break;
		case "Recebimento":
			transacaoRecebimentoProduto(produto, quantidade);
			break;
		case "Devolução":
			transacaoDevolucaooProduto(produto, quantidade);
			break;
		case "Transferencia":
			transacaoTransferenciaProduto(produto, empresaOrigem, empresaDestino, quantidade);
			break;
		default:

			System.out.println("Tipo de transação não reconhecido: " + tipoTransacao);
			break;
		}
		alertaEstoque(produto, now);
	}

	public void transacaoVendaProduto(Produto produto, int quantidade) {
		produto.removerEstoque(quantidade);
	}

	public void transacaoRecebimentoProduto(Produto produto, int quantidade) {
		produto.adicionarEstoque(quantidade);
	}

	public void transacaoDevolucaooProduto(Produto produto, int quantidade) {
		produto.adicionarEstoque(quantidade);
	}

	public void transacaoTransferenciaProduto(Produto produto, Empresa empresaOrigem, Empresa empresaDestino,
			int quantidade) throws Exception {

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
	
	 public void alertaEstoque(Produto produto, Date now) throws EstoqueNegativoException {
	    	
		 if(produto.getQtdMinimaEstoque() < produto.getQtdMinimaEstoque()) {
	    		System.out.printf("estoque baixo produto %s total %s fornecedor %s ", produto.getId(), produto.getQuantidadeEmEstoque(), produto.getFornecedor().getNome());
	    	}
	    	
	    	if(isProximoVencimento(now,produto.getValidade())) {
	    		System.out.print("produtos próximos de vencer");
	    		
	    	Double valorDesconto = produto.getPreco() * 0.2;
	    	Double valorFinal = produto.getPreco() - valorDesconto;
	    	produto.setPreco(valorFinal);
	    	}
			
	    	if(produto.getQuantidadeEmEstoque() < 0 ) {
				produto.setQuantidadeEmEstoque(0);
				throw new EstoqueNegativoException("Estoque negativo");
			}
		    	
	    }
	 
	 public  boolean isProximoVencimento(Date now, Date validade) {
	        long diffInMillis = Math.abs(now.getTime() - validade.getTime());
	        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
	        return diffInDays == 10;
	    }
}
