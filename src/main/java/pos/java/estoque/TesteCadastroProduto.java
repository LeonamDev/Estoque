/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.estoque;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import pos.java.estoque.model.Produto;
import pos.java.estoque.repository.ProdutoRepository;
import pos.java.estoque.service.CadastroProdutoService;
import pos.java.estoque.service.ServiceException;
import pos.java.estoque.util.JpaUtil;

public class TesteCadastroProduto {

    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction trx = manager.getTransaction();
        ProdutoRepository produtos = new ProdutoRepository(manager);
        
        CadastroProdutoService servico
                = new CadastroProdutoService(produtos);
        
        Produto produto = new Produto();
        produto.setNome("Picanha");
        produto.setPrecoCusto(new BigDecimal(70.4));
// regra de negócio não permite quantidade menor que 0
        produto.setQuantidadeEstoque(2);
        
        try {
            trx.begin();
            servico.cadastrar(produto);
            trx.commit();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            manager.close();
        }
    }
}
