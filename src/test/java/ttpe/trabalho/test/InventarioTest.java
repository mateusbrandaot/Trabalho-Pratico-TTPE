package ttpe.trabalho.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ttpe.trabalho.exception.DescricaoEmBrancoException;
import ttpe.trabalho.exception.ValorInvalidoException;
import ttpe.trabalho.model.Empresa;
import ttpe.trabalho.model.Estoque;
import ttpe.trabalho.model.Fornecedor;
import ttpe.trabalho.model.Produto;

public class InventarioTest {

    private Estoque estoque;

    @BeforeEach
    public void setUp() {
    	estoque = new Estoque(); //inicializa o inventario para cada teste
    }

    @Test
    @DisplayName("Adicionar produto ao inventário")
    void testAdicionarProduto() throws DescricaoEmBrancoException, ValorInvalidoException {
    	Empresa empresa = new Empresa(1, "35737451000190", "MATRIZ", "rua portugal");
    	Fornecedor fornecedor = new Fornecedor(1, "fornecedor test", "6198460585", "endereço teste");
    	Date now = new Date();
    	Produto novoProduto = new Produto("1", "Novo Produto", "Descrição", "123456", 10.0, 5, empresa, fornecedor, 30, now);
    	estoque.adicionarProduto(novoProduto);
        assertAll("inventario",
            () -> assertNotNull(estoque.getProdutoPorId("1")),
            () -> assertEquals("Novo Produto", estoque.getProdutoPorId("1").getNome())
        );
    }
    
}
