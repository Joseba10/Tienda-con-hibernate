package com.ipartek.TIPOS;

public class Carrito {

	private int cantidad;
	private int productos;
	private int id_usuario;

	public int getProductos() {
		return productos;
	}

	public void setProductos(int productos) {
		this.productos = productos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public Carrito(int cantidad, int productos, int id_usuario) {
		super();
		this.cantidad = cantidad;
		this.productos = productos;
		this.id_usuario = id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Carrito() {

	}
}
