package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.TIPOS.Usuario;

public class UsuarioDAOMySQL extends IpartekDAOMySQL implements UsuarioDAO {

	private final static String FIND_ALL = "Select * from usuarios";
	private final static String FIND_ID = "Select * from usuarios where id=?";
	private final static String INSERT = "Insert into usuarios(username,password,id_roles,nombre_completo)Values(?,?,?,?)";
	private final static String Update = "Update usuarios Set password=?,nombre_completo=?,id_roles=? where username=?";
	private final static String Delete = "Delete from usuarios where username=?";
	private final static String FIND_BY_NAME = "Select * from usuarios where username=?";

	private final static String DELETE_TABLE_USUARIOS = "Delete from usuarios";
	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate, psDelete, psFindByName;
	public ResultSet rs = null;
	// LOG4J
	private static Logger log = Logger.getLogger(UsuarioDAOMySQL.class);

	public boolean validar(Usuario usuario) {
		this.abrir();
		Usuario[] usuariosArr = this.findAll();
		this.cerrar();

		for (Usuario u : usuariosArr) {
			if (u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(usuario.getPassword())) {

				return true;
			}

		}
		return false;
	}

	public UsuarioDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public UsuarioDAOMySQL() {

	}

	public Usuario[] findAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			psFindAll = con.prepareStatement(FIND_ALL);
			rs = psFindAll.executeQuery();
			Usuario usuario;
			while (rs.next()) {

				// System.out.println(rs.getString("username"));
				usuario = new Usuario(0, rs.getInt("id_roles"), rs.getString("username"), rs.getString("password"), rs.getString("nombre_completo"));

				usuarios.add(usuario);

			}

		} catch (SQLException e) {

			throw new DAOException("Error en FindAll", e);
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psFindAll != null)
					psFindAll.close();

			} catch (SQLException e) {

			}

		}
		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	public Usuario findById(int id) {

		Usuario usuario = null;

		try {
			psFindById = con.prepareStatement(FIND_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery(); // Conjunto de resultados que salen
											// de la consulta
			if (rs.next()) {

				usuario = new Usuario(0, 0, "username", "password", "nombre_completo");

				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));

			}
		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
		}

		return usuario;

	}

	public Usuario findByName(String username) {

		Usuario usuario = null;

		ResultSet rs = null;

		try {
			psFindByName = con.prepareStatement(FIND_BY_NAME);

			psFindByName.setString(1, username);
			rs = psFindByName.executeQuery(); // Conjunto de resultados que salen
												// de la consulta
			if (rs.next()) {

				usuario = new Usuario(0, 0, "username", "password", "nombre_completo");

				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));

			}
		} catch (SQLException e) {
			throw new DAOException("Error en FindByName", e);
		} finally {

			cerrar(psFindByName, rs);
		}

		return usuario;

	}

	public int insert(Usuario usuario) {

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, usuario.getUsername());
			psInsert.setString(2, usuario.getPassword());
			psInsert.setInt(3, usuario.getId_roles());
			psInsert.setString(4, usuario.getNombre_completo());

			log.info(usuario.getUsername());
			log.info(usuario.getPassword());
			log.info(usuario.getId_roles());
			log.info(usuario.getNombre_completo());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La insercion ha devuelto un valor " + res);

			ResultSet generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);

			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en Insert", e);
		} finally {

			cerrar(psDelete);
		}

	}

	private void cerrar(PreparedStatement ps) {
		cerrar(ps, null);
	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				con.close();

			if (psFindAll != null)
				psFindAll.close();

		} catch (SQLException e) {

		}

	}

	public void update(Usuario usuario) {
		try {
			psUpdate = con.prepareStatement(Update);
			// psUpdate.setString(1, usuario.getUsername());
			psUpdate.setString(1, usuario.getPassword());
			psUpdate.setString(2, usuario.getNombre_completo());
			psUpdate.setInt(3, usuario.getId_roles());
			psUpdate.setString(4, usuario.getUsername());

			log.info(usuario.getUsername());
			log.info(usuario.getPassword());
			log.info(usuario.getId_roles());
			log.info(usuario.getNombre_completo());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error al actualizar", e);
		}

	}

	public void delete(Usuario usuario) {

		delete(usuario.getUsername());

	}

	public void delete(String username) {
		try {
			psDelete = con.prepareStatement(Delete);
			psDelete.setString(1, username);
			int res = psDelete.executeUpdate();
			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);
		} catch (Exception e) {

			throw new DAOException("Error en el delete ", e);
		}

	}

	public void deleteTableUsuarios() {

		try {
			psDelete = con.prepareStatement(DELETE_TABLE_USUARIOS);
			psDelete.executeUpdate();

		} catch (Exception e) {

			throw new DAOException("Error en delete table", e);

		} finally {

			cerrar(psDelete);
		}
	}

}
