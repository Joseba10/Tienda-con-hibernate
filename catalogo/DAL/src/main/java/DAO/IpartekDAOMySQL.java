package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IpartekDAOMySQL implements IpartekDAO {

	public Connection con;
	private String url = "jdbc:mysql://localhost/catalogoapp";
	private String mysqluser = "root";
	private String mysqlpassword = "";

	public IpartekDAOMySQL() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public IpartekDAOMySQL(String url2, String mysqlUser2, String mysqlPass) {

	}

	public void abrir() {

		try {

			con = DriverManager.getConnection(url, mysqluser, mysqlpassword);
		} catch (SQLException e) {

			throw new DAOException("Error de conexion a la base de datos", e);
		} catch (Exception e) {
			throw new DAOException("Error no esperado", e);
		} finally {

		}
	}

	public void cerrar() {

		try {
			if (con != null && !con.isClosed()) {

				con.close();
			}
			con = null;
		} catch (SQLException e) {

			throw new DAOException("Error de conexion a la base de datos", e);

		} catch (Exception e) {
			throw new DAOException("Error no esperado", e);
		}
	}

	public void iniciarTransaccion() {

		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException("Error al  desactivar Autocommit", e);
		}
	}

	public void confirmarTransaccion() {
		try {
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException("Error al  confirmar transaccion", e);
		}
	}

	public void deshacerTransaccion() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException("Error al deshacer transaccion", e);
		}
	}
}
