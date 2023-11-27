package ttpe.trabalho.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
   
	private Map<String, Produto> produtos =  new HashMap<>();
    
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public Produto getProduto(String id) {
        return produtos.get(id);
    }

    public boolean verificarEstoqueBaixo(String id, int limite) {
        Produto produto = produtos.get(id);
        return produto != null && produto.getQuantidadeEmEstoque() < limite;
    }
}
