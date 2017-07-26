package DAO;

public class ProductoDAOFactory {

	public static ProductoDAO getProductoDAO() {
		// return new UsuarioDalUsuarioUnico();
		// Crea un nuevo usuario cada vez que se ejecute
		return new ProductoDAOMySQL();
	}
}
