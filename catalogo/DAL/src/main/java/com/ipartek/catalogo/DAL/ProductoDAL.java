package com.ipartek.catalogo.DAL;

import com.ipartek.TIPOS.Producto;

//Aqui es donde se guardan los registros de los productos,se ponen las caracteristicas
public interface ProductoDAL {

	public void alta(Producto producto);

	public void modificar(Producto producto);

	public void borrar(Producto producto);

	public Producto buscarPorId(int id);

	public Producto[] buscarTodosLosProductos();

	public boolean validar(Producto producto);

}
