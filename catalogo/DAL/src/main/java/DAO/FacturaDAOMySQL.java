package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.TIPOS.Factura;

public class FacturaDAOMySQL extends IpartekDAOMySQL implements FacturaDAO {

	private final static String FIND_ALL = "Select * from facturas";
	private final static String FIND_ID = "Select * from facturas where id=?";
	private final static String INSERT = "Insert into facturas(numero_factura,id_usuarios,fecha)Values(?,?,?)";
	private final static String Update = "Update facturas Set numero_factura=?,id_usuarios=?,fecha=? where id=?";
	private final static String Delete = "Delete from facturas where id=?";
	private final static String VERCANTIDADVENDIDA = "Select * FROM facturas_productos WHERE id_facturas=?";
	private final static String GET_MAX_ID = "SELECT MAX(ID) FROM facturas";
	private final static String Find_Produc_By_Factura = "Select * from productos ,facturas_productos where facturas_productos.id_facturas=? and productos.id=facturas_productos.id_productos";
	private final static String Registrar_Productos = "Insert into facturas_productos(id_facturas,id_productos,cantidad) values(?,?,?)";

	private PreparedStatement psVerCantidadVendida, psFindAll, psFindById, psInsert, psUpdate, psDelete, psBuscarfactura, psRegistrar_Productos, psGetMaxId;
	public ResultSet rs = null;

	// LOG4J
	private static Logger log = Logger.getLogger(ProductoDAOMySQL.class);

	public FacturaDAOMySQL() {

	}

	public int insert(Factura factura) {
		ResultSet generatedKeys = null;
		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			psInsert.setInt(1, factura.getNumero_factura());
			psInsert.setInt(2, factura.getId_usuarios());
			psInsert.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
			int res = psInsert.executeUpdate();
			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor " + res);
			}
			generatedKeys = psInsert.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new DAOException("No se ha recibido la clave generada");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en el insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}
	}

	@Override
	public void delete(Factura factura) {
		try {
			psDelete = con.prepareStatement(Delete);
			psDelete.setInt(1, factura.getId());
			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);
		} catch (Exception e) {

			throw new DAOException("Error en el delete ", e);
		}

	}

	public int getMaxId() {

		ResultSet rs;
		int maxId = 0;
		try {
			psGetMaxId = con.prepareStatement(GET_MAX_ID);

			rs = psGetMaxId.executeQuery();

			while (rs.next()) {
				maxId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener maxId", e);
		} finally {
			cerrar(psGetMaxId);
		}

		return maxId;
	}

	@Override
	public void update(Factura factura) {
		try {
			psUpdate = con.prepareStatement(Update);

			psUpdate.setInt(1, factura.getNumero_factura());
			psUpdate.setInt(2, factura.getId_usuarios());
			psUpdate.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
			psUpdate.setInt(4, factura.getId());

			log.info(factura.getId());
			log.info(factura.getNumero_factura());
			log.info(factura.getId_usuarios());
			log.info(factura.getFecha());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error al actualizar", e);
		}
	}

	private void cerrar(PreparedStatement ps) {

		cerrar(ps, null);

	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();

		} catch (Exception e) {

			throw new DAOException("Error en el cierre de ps o rs", e);

		}
	}

	// Adaptar

	public Factura findid(int id) {

		Factura factura = null;

		try {
			psFindById = con.prepareStatement(FIND_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery(); // Conjunto de resultados que salen
											// de la consulta
			if (rs.next()) {

				factura = new Factura();

				factura.setId(rs.getInt("id"));
				factura.setNumero_factura(rs.getInt("numero_factura"));
				factura.setId_usuarios(rs.getInt("id_usuarios"));
				factura.setFecha(rs.getDate("fecha"));

			}
		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
		}

		return factura;

	}

	public Factura[] findAll() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		try {
			psFindAll = con.prepareStatement(FIND_ALL);
			rs = psFindAll.executeQuery();
			Factura factura;
			while (rs.next()) {

				factura = new Factura();
				// System.out.println(rs.getString("username"));

				factura.setId(rs.getInt("id"));
				factura.setNumero_factura(rs.getInt("numero_factura"));
				factura.setId_usuarios(rs.getInt("id_usuarios"));
				factura.setFecha(rs.getDate("fecha"));

				facturas.add(factura);

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
		return facturas.toArray(new Factura[facturas.size()]);
	}

	public int insertFacturaProducto(int id_factura, int id_producto, int cantidad) {
		try {
			psRegistrar_Productos = con.prepareStatement(Registrar_Productos);
			psRegistrar_Productos.setInt(1, id_factura);
			psRegistrar_Productos.setInt(2, id_producto);
			psRegistrar_Productos.setInt(3, cantidad);
			int res = psRegistrar_Productos.executeUpdate();
			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor " + res);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en al insertar producto en la factura", e);
		} finally {
			cerrar(psRegistrar_Productos);
		}
		return 1;
	}
}
