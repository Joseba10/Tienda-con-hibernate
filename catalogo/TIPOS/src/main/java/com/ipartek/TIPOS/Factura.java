package com.ipartek.TIPOS;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {

	public static int siguiente_factura;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int numero_factura;

	@ManyToOne
	@Column(name = "id_usuarios")
	private Usuario usuario;

	private Date fecha;

	public Factura() {

	}

	public Factura(int numero_facturas, Usuario id_usuarios, Date fecha) {

		this.numero_factura = numero_facturas;
		this.usuario = id_usuarios;
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

	public Usuario getId_usuarios() {
		return usuario;
	}

	public Usuario setId_usuarios(Usuario id_usuarios) {
		return this.usuario = id_usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Factura(Usuario id_usuarios, Date fecha) {
		super();

		this.numero_factura = siguiente_factura;
		this.usuario = id_usuarios;
		this.fecha = fecha;
		siguiente_factura++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + numero_factura;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (numero_factura != other.numero_factura)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}