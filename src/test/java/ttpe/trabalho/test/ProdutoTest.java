package ttpe.trabalho.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import ttpe.trabalho.exception.DescricaoEmBrancoException;
import ttpe.trabalho.exception.ValorInvalidoException;
import ttpe.trabalho.model.Empresa;
import ttpe.trabalho.model.Fornecedor;
import ttpe.trabalho.model.Produto;

public class ProdutoTest {

    @Test(expected = DescricaoEmBrancoException.class)
    public void deveLancarExcecaoParaDescricaoEmBranco() throws DescricaoEmBrancoException, ValorInvalidoException {
        
    	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
    	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
    	Date now = new Date();
    	Produto produto = new Produto("1", "", "Descrição", "123456", 10.0, 5, empresa, fornecedor, 30, now);
    }

    @Test(expected = ValorInvalidoException.class)
    public void deveLancarExcecaoParaValorInvalido() throws DescricaoEmBrancoException, ValorInvalidoException {
    	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
    	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
    	Date now = new Date();
    	Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", -1.0, 5, empresa, fornecedor, 30, now);
    }

    @Test
    public void deveCadastrarProdutoValido() {
        try {
        	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
        	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
        	Date now = new Date();
        	Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", 10.0, 5, empresa, fornecedor, 30, now);
            assertNotNull(produto);
        } catch (DescricaoEmBrancoException | ValorInvalidoException e) {
            fail("Não deveria lançar exceção para dados válidos.");
        }
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10}) 
    void testAdicionarEstoque(int quantidade) throws DescricaoEmBrancoException, ValorInvalidoException {
    	Date now = new Date();
        Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", 10.50, quantidade, null, null, 30, now);
        int estoqueInicial = produto.getQuantidadeEmEstoque();
        
        produto.adicionarEstoque(quantidade);

        Assertions.assertEquals(estoqueInicial + quantidade, produto.getQuantidadeEmEstoque());
    }
    
 
    private static Stream<Arguments> removerEstoqueArguments() {
        return Stream.of(
            Arguments.of(5, 3, true),  
            Arguments.of(5, 5, true),
            Arguments.of(5, 6, false)
        );
    }

    @ParameterizedTest
    @MethodSource("removerEstoqueArguments")
    void testRemoverEstoque(int estoqueInicial, int quantidadeARemover, boolean resultadoEsperado) throws Exception {
    	Date now = new Date();
    	Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", 10.50, estoqueInicial, null, null, 30, now);

        boolean resultado = produto.removerEstoque(quantidadeARemover);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }
}
    


