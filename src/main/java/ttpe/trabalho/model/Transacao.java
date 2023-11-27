package ttpe.trabalho.model;

import java.util.Date;

public class Transacao {

	    private Produto produto;
	    
	    private int quantidade;
	    
	    private Date dataTransacao;
	    
	    private String tipoTransacao; // Ex: "Venda", "Recebimento", "Devolução", etc.

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		public int getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}

		public Date getDataTransacao() {
			return dataTransacao;
		}

		public void setDataTransacao(Date dataTransacao) {
			this.dataTransacao = dataTransacao;
		}

		public String getTipoTransacao() {
			return tipoTransacao;
		}

		public void setTipoTransacao(String tipoTransacao) {
			this.tipoTransacao = tipoTransacao;
		}

	    

	
}
