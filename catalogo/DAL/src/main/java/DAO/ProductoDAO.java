package DAO;

import com.ipartek.TIPOS.Producto;

public interface ProductoDAO extends IpartekDAO {

	public Producto[] findAll(); // Select

	public Producto findById(int id);

	public Producto findByName(String nombre);

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void delete(int id);

	public void cerrar();

	public boolean validar(Producto producto);

	public void deleteTableProductos();

}
