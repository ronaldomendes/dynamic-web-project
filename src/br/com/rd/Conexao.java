package br.com.rd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
	private Connection conn;

	public Conexao() throws Exception {
		this.obterConexao();
	}

	public void obterConexao() throws Exception {
		String url = "jdbc:mariadb://localhost/webdb";
		String user = "root";
		String password = "";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(String.format("Erro ao conectar o banco: %s", e.getMessage()));
		}
	}

	public List<String> buscarLojas() throws Exception {
		List<String> lojas = new ArrayList<String>();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery("select descricao from tb_loja");
			while (rs.next()) {
				lojas.add(rs.getString("descricao"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(String.format("Erro ao executar ao consultar tb_loja: %s", e.getMessage()));
		} finally {
			rs.close();
			st.close();
			conn.close();
		}

		return lojas;
	}
}
