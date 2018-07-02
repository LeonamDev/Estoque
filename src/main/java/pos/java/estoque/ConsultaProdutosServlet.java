package pos.java.estoque;

import pos.java.estoque.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.estoque.repository.ProdutoRepository;
import pos.java.estoque.util.JpaUtil;

@WebServlet("/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        EntityManager manager = JpaUtil.getEntityManager();

        ProdutoRepository produtos = new ProdutoRepository(manager);

        try {
            request.setAttribute("versaoSistema", "1.0-beta");
            request.setAttribute("agora", new Date());

            String nome = request.getParameter("nome");
//            TypedQuery<Produto> query = manager.createQuery("select p from Produto p", Produto.class);
            List<Produto> todosProdutos = produtos.porNomeNaoExato(nome);

           /* if (todosProdutos.isEmpty()) {
                response.sendRedirect("nenhum-produto-encontrado.html");
                return;
            }*/

            request.setAttribute("produtos", todosProdutos);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/consulta-produtos.jsp");
            dispatcher.forward(request, response);

/* imprime a abertura das tags html e body
            out.println("<html>");
            out.println("<body>");
// imprime a abertura da tag table e linha do cabeçalho
            out.println("<table border=\"1\">");
            out.println("<tr><th>Nome</th><th>Preço</th>"
                    + "<th>Quantidade</th></tr>");

            for (Produto produto : todosProdutos) {
                out.println("<tr>");
                out.println("<td>" + produto.getNome() + "</td>");
                out.println("<td>" + produto.getPrecoCusto() + "</td>");
                out.println("<td>" + produto.getQuantidadeEstoque() + "</td>");
                out.println("</tr>");
            }
// imprime o fechamento da tabela e das tags body e html
            out.println("</table>");
            out.println("</body>");
            out.println("</html>"); */
        } finally {
            manager.close();
        }
    }
}
