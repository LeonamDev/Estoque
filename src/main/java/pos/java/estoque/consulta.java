/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.estoque;

import pos.java.estoque.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.estoque.util.JpaUtil;

/**
 *
 * @author DesenvolvedorJava
 */
@WebServlet(name = "consulta", urlPatterns = {"/consulta"})
public class consulta extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        EntityManager manager = JpaUtil.getEntityManager();

        try {
// consulta os produtos
            TypedQuery<Produto> query  = manager.createQuery("select p from Produto p", Produto.class);
            List<Produto> todosProdutos = query.getResultList();
// imprime a abertura das tags html e body
            out.println("<html>");
            out.println("<body>");
// imprime a abertura da tag table e linha do cabeçalho
            out.println("<table border=\"1\">");
            out.println("<tr><th>Nome</th><th>Preço</th>"
                    + "<th>Quantidade</th></tr>");
// imprime cada linha da tabela
            for (Produto produto : todosProdutos) {
                out.println("<tr>");
                out.println("<td>" + produto.getNome() + "</td>");
                out.println("<td>" + produto.getPrecoCusto()
                        + "</td>");
                out.println("<td>" + produto.getQuantidadeEstoque()
                        + "</td>");
                out.println("</tr>");
            }
// imprime o fechamento da tabela e das tags body e html
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            manager.close();
        }
    }
}
