package br.com.rd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MinhaServlet extends HttpServlet {
//	executando o método e retornando o valor que estiver contido

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		// respondendo o valor que deve ser retornado

		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Minha Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Minha primeira servlet está funcionando...</h1>");

			Conexao conn = new Conexao();
			List<String> lojas = conn.buscarLojas();
			
			out.println("<ul>");
			for(String loja: lojas) {
				out.println(String.format("<li>%s</li>", loja));
			}
			out.println("</ul>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(String.format("<h2>Erro ao bustar as lojas: %s</h2>", e.getMessage()));
		} finally {
			out.println("</body>");
			out.println("</html>");			
		}
	}
}
