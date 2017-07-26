package DAO;

import com.ipartek.TIPOS.Factura;

public interface FacturaDAO extends IpartekDAO {

	public int insert(Factura factura);

	public void delete(Factura factura);

	public void update(Factura factura);

	public Factura findid(int id);

	public Factura[] findAll();

	public int getMaxId();

	public int insertFacturaProducto(int idfactura, int idproductos, int cantidad);

}
