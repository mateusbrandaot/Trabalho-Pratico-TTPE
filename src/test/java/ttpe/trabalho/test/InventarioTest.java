package ttpe.trabalho.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ttpe.trabalho.model.Inventario;
import ttpe.trabalho.model.Produto;

public class InventarioTest {

    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario(); // Inicializa o inventario antes de cada teste
    }

    @Test
    @DisplayName("Adicionar produto ao inventário")
    void testAdicionarProduto() {
        Produto novoProduto = new Produto("2", "Novo Produto", "Nova Descrição", 20.0, 100);
        inventario.adicionarProduto(novoProduto);
        assertAll("inventario",
            () -> assertNotNull(inventario.getProduto("2")),
            () -> assertEquals("Novo Produto", inventario.getProduto("2").getNome())
        );
    }
    
}
