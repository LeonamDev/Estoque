package pos.java.estoque.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.estoque.model.Produto;

public class ProdutoRepository {

    private EntityManager manager;

    public ProdutoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Produto> todos() {
        TypedQuery<Produto> query = manager.createQuery("select p from Produto p",
                Produto.class);
        return query.getResultList();
    }
    
    public List<Produto> porNomeNaoExato(String nome) {
        TypedQuery<Produto> query = manager.createQuery(
        "from  Produto p where upper(p.nome) like upper(:nome)",Produto.class);
        query.setParameter("nome", "%" + (nome == null ? "" : nome)
        + "%");
        return query.getResultList();
        }
    
    public void adicionar(Produto produto) {
        this.manager.persist(produto);
    }


}

