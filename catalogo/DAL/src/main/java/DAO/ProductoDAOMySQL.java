package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.TIPOS.Producto;

public class ProductoDAOMySQL extends IpartekDAOMySQL implements ProductoDAO {

	private final static String FIND_ALL = "Select * from productos";
	private final static String FIND_BY_NAME = "SELECT * FROM productos WHERE nombre = ?";
	private final static String INSERT = "Insert into productos (nombre,precio,descripcion,imagen,cantidad) Values (?,?,?,?,?)";
	private final static String Update = "Update productos Set nombre=?, precio=?,descripcion=?,imagen=?,cantidad=? where id=?";
	private final static String Delete = "Delete from productos where id=?";
	private final static String FIND_BY_ID = "Select * from productos where id=?";
	private final static String cogercantidad = "Update productos Set cantidad=? where id=?";
	private final static String DELETE_TABLE_PRODUCTOS = "Delete from productos";
	private PreparedStatement psVerCantidad, psFindAll, psFindById, psInsert, psUpdate, psDelete, psFindByName;
	public ResultSet rs = null;
	// LOG4J
	private static Logger log = Logger.getLogger(ProductoDAOMySQL.class);

	public boolean validar(Producto producto) {
		this.abrir();
		Producto[] productoArr = this.findAll();
		this.cerrar();

		for (Producto u : productoArr) {
			if (u.getId() == producto.getId()) {

				return true;
			}

		}
		return false;
	}

	public ProductoDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public ProductoDAOMySQL() {

	}

	public Producto[] findAll() {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try {
			psFindAll = con.prepareStatement(FIND_ALL);
			rs = psFindAll.executeQuery();
			Producto producto;
			while (rs.next()) {

				producto = new Producto();
				// System.out.println(rs.getString("username"));

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setImagen(rs.getInt("imagen"));
				producto.setCantidad(rs.getInt("cantidad"));

				productos.add(producto);

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
		return productos.toArray(new Producto[productos.size()]);
	}

	public Producto findById(int id) {

		Producto producto = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery(); // Conjunto de resultados que salen
											// de la consulta
			if (rs.next()) {

				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setImagen(rs.getInt("imagen"));
				producto.setCantidad(rs.getInt("cantidad"));

			}
		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
		}

		finally {

			cerrar(psFindById, rs);
		}

		return producto;

	}

	public Producto findByName(String nombre) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			log.info(con.isClosed());
			log.info(nombre);
			psFindByName = con.prepareStatement(FIND_BY_NAME);
			log.info(psFindByName);
			psFindByName.setString(1, nombre);
			rs = psFindByName.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setImagen(rs.getInt("imagen"));
				producto.setCantidad(rs.getInt("cantidad"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findByName", e);
		} finally {
			cerrar(psFindByName, rs);
		}

		return producto;
	}

	public int insert(Producto producto) {

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, producto.getNombre());
			psInsert.setDouble(2, producto.getPrecio());
			psInsert.setString(3, producto.getDescripcion());
			psInsert.setInt(4, producto.getImagen());
			psInsert.setInt(5, producto.getCantidad());

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
				rs.close();

			if (psFindAll != null)
				psFindAll.close();

		} catch (SQLException e) {

		}

	}

	public void update(Producto producto) {
		try {
			psUpdate = con.prepareStatement(Update);
			// psUpdate.setString(1, usuario.getUsername());
			psUpdate.setString(1, producto.getNombre());
			psUpdate.setDouble(2, producto.getPrecio());
			psUpdate.setString(3, producto.getDescripcion());
			psUpdate.setInt(4, producto.getImagen());
			psUpdate.setInt(5, producto.getCantidad());
			psUpdate.setInt(6, producto.getId());

			log.info(producto.getNombre());
			log.info(producto.getPrecio());
			log.info(producto.getDescripcion());
			log.info(producto.getImagen());
			log.info(producto.getCantidad());
			log.info(producto.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error al actualizar", e);
		}

	}

	public void delete(Producto producto) {

		delete(producto.getId());

	}

	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(Delete);
			psDelete.setInt(1, id);
			int res = psDelete.executeUpdate();
			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);
		} catch (Exception e) {

			throw new DAOException("Error en el delete ", e);
		}

	}

	public void deleteTableProductos() {

		try {
			psDelete = con.prepareStatement(DELETE_TABLE_PRODUCTOS);
			psDelete.executeUpdate();

		} catch (Exception e) {

			throw new DAOException("Error en delete table", e);

		} finally {

			cerrar(psDelete);
		}
	}

}
