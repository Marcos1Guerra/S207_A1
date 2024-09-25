package br.inatel.cdg.compras;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarrinhoCompra {

    // Teste inicial básico (Padrão 1)
    @Test
    public void testSomaTotalCompra() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150); // 6 * 150 = 900
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100); // 4 * 100 = 400

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);

        assertEquals(1300, carrinho.somaTotal(), 0.01); // Total should be 1300
    }

    // Teste inicial básico (Padrão 1) + algo diferente (teste valor total de itens)
    // (Padrão 2)
    @Test
    public void testSomaTotalQtd() throws NumeroNegativoException {

        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);
        assertEquals(10, carrinho.somaTotalItens(), 0.01);

    }

    // Teste negativo (Padrão 3) forçando uma situação inesperada
    @Test(expected = NumeroNegativoException.class)
    public void testSomaNegativo() throws NumeroNegativoException {

        Brownie bw1 = new Brownie("Brownie Nutella", -6, 150);
        Brownie bw2 = new Brownie("Brownie Doce de Leite", -4, 100);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);

    }

    // Teste integração (Padrão 4) 2 classes (Carrinho e Cupom sendo testadas
    // juntas).
    @Test
    public void testIntegracaoCupom() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150); // 6 * 150 = 900
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100); // 4 * 100 = 400

        CarrinhoCompra carrinho = new CarrinhoCompra();
        CupomDesconto cupom = new CupomDesconto(0.20);
        carrinho.setCupom(cupom);

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);

        assertEquals(1040, carrinho.somaTotal(), 0.1); // Total com desconto de 20%
    }

    // Teste inicial básico (Padrão 5) verificando o desconto no cupom sem itens
    @Test
    public void testCupomSemItens() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        CupomDesconto cupom = new CupomDesconto(0.50);
        carrinho.setCupom(cupom);

        assertEquals(0, carrinho.somaTotal(), 0.01);
    }

    // Teste de comportamento (Padrão 6) carrinho com múltiplos itens e diferentes
    // quantidades
    @Test
    public void testCarrinhoMultiplosItens() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 2, 150);  // 2 * 150 = 300
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 3, 100);  // 3 * 100 = 300
        Brownie bw3 = new Brownie("Brownie Chocolate", 5, 200);  // 5 * 200 = 1000
    
        CarrinhoCompra carrinho = new CarrinhoCompra();
    
        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);
        carrinho.adiciona(bw3);
    
        assertEquals(1600, carrinho.somaTotal(), 0.01);  // Total = 300 + 300 + 1000 = 1600
    }    

    // Teste (Padrão 7) verificando a adição de vários itens iguais
    @Test
    public void testAdicionaItensIguais() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 5, 100);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw1); // adicionando o mesmo item duas vezes

        assertEquals(1000, carrinho.somaTotal(), 0.01);
    }

    // Teste inicial básico (Padrão 8) verificando a quantidade total de itens após
    // remoção
    @Test
    public void testRemoveItemQtd() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);
        carrinho.remove(bw2);

        assertEquals(6, carrinho.somaTotalItens(), 0.01);
    }

    // Teste de limite (Padrão 9) forçando a quantidade máxima de itens no carrinho
    @Test
    public void testCarrinhoLimiteMax() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 100, 10);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        for (int i = 0; i < 100; i++) {
            carrinho.adiciona(bw1); // adiciona 100 itens
        }

        assertEquals(10000, carrinho.somaTotalItens(), 0.01);
    }

    // Teste de comportamento (Padrão 10) verificando o carrinho após resetar
    // (esvaziar)
    @Test
    public void testCarrinhoReset() throws NumeroNegativoException {
        Brownie bw1 = new Brownie("Brownie Nutella", 5, 150);
        Brownie bw2 = new Brownie("Brownie Doce de Leite", 3, 100);

        CarrinhoCompra carrinho = new CarrinhoCompra();

        carrinho.adiciona(bw1);
        carrinho.adiciona(bw2);
        carrinho.reset(); // esvazia o carrinho

        assertEquals(0, carrinho.somaTotalItens(), 0.01);
        assertEquals(0, carrinho.somaTotal(), 0.01);
    }

}
