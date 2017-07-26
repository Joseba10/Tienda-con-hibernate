package com.ipartek.TIPOS;

import java.util.Date;

public class Factura {

	public static int siguiente_factura;
	private int id;
	private int numero_factura;
	private int id_usuarios;
	private Date fecha;

	public Factura() {

	}

	public Factura(int numero_facturas, int id_usuarios, Date fecha) {

		this.numero_factura = numero_facturas;
		this.id_usuarios = id_usuarios;
		this.fecha = fecha;

	}

	public int getNumero_factura() {
		return numero_factura;
	}

	public void setNumero_factura(int numero_factura) {
		this.numero_factura = numero_factura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuarios() {
		return id_usuarios;
	}

	public void setId_usuarios(int id_usuarios) {
		this.id_usuarios = id_usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Factura(int id_usuarios, Date fecha) {
		super();

		this.numero_factura = siguiente_factura;
		this.id_usuarios = id_usuarios;
		this.fecha = fecha;
		siguiente_factura++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + id_usuarios;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (id_usuarios != other.id_usuarios)
			return false;
		return true;
	}

}
