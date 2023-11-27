package ttpe.trabalho.model;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<String, Produto> produtos;

    public Inventario() {
        produtos = new HashMap<>();
    }

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
