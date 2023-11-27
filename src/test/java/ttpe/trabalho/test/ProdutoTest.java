package ttpe.trabalho.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

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
    	Produto produto = new Produto("1", "", "Descrição", "123456", 10.0, 5, empresa, fornecedor);
    }

    @Test(expected = ValorInvalidoException.class)
    public void deveLancarExcecaoParaValorInvalido() throws DescricaoEmBrancoException, ValorInvalidoException {
    	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
    	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
    	Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", -1.0, 5, empresa, fornecedor);
    }

    @Test
    public void deveCadastrarProdutoValido() {
        try {
        	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
        	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
        	Produto produto = new Produto("1", "Produto Teste", "Descrição", "123456", 10.0, 5, empresa, fornecedor);
            assertNotNull(produto);
        } catch (DescricaoEmBrancoException | ValorInvalidoException e) {
            fail("Não deveria lançar exceção para dados válidos.");
        }
    }
}

