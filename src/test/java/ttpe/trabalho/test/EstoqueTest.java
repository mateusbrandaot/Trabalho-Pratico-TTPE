package ttpe.trabalho.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ttpe.trabalho.exception.ProdutoInvalidoException;
import ttpe.trabalho.model.Estoque;
import ttpe.trabalho.model.Produto;

class EstoqueTest {

    private Estoque estoque;

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
        // Adicionar alguns produtos ao estoque para os testes
       // estoque.adicionarProduto(new Produto(/* parâmetros do construtor */)); // Exemplo
        // Continue adicionando conforme necessário
    }

    @ParameterizedTest
    @ValueSource(strings = {"idValido", "idInvalido"}) // Adicione IDs de exemplo
    void testGetProdutoPorId(String id) {
        try {
            Produto produto = estoque.getProdutoPorId(id);
            Assertions.assertNotNull(produto);
        } catch (ProdutoInvalidoException e) {
            Assertions.assertTrue(id.equals("idInvalido"));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"nomeValido", "nomeInvalido"}) // Adicione nomes de exemplo
    void testGetProdutoPorNome(String nome) {
        try {
            Produto produto = estoque.getProdutoPorNome(nome);
            Assertions.assertNotNull(produto);
        } catch (ProdutoInvalidoException e) {
            Assertions.assertTrue(nome.equals("nomeInvalido"));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"codigoBarraValido", "codigoBarraInvalido"}) // Adicione códigos de barras de exemplo
    void testGetProdutoPorCodigoBarra(String codigoBarra) {
        try {
            Produto produto = estoque.getProdutoPorCodigoBarra(codigoBarra);
            Assertions.assertNotNull(produto);
        } catch (ProdutoInvalidoException e) {
            Assertions.assertTrue(codigoBarra.equals("codigoBarraInvalido"));
        }
    }
}
