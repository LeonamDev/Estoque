<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSP que diz "Olá" --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            String nome = request.getParameter("nome");
            if (nome == null) {
                nome = "desconhecido";
            }
            nome = nome.toUpperCase();
        %>
        <!-- Mensagem de cumprimento -->
        Olá <strong><% out.print(nome);%></strong>!
        Olá <strong><%= nome.toUpperCase()%></strong>!
        </body>
        </html>