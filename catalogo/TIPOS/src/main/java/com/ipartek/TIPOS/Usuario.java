package com.ipartek.TIPOS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	private int id;
	private Rol roles;
	private String nombre_completo;
	private String password;

	@ManyToOne
	@Column(name = "username")
	private Factura username;

	private String errores;

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public Usuario(int id, int id_roles, Usuario username, String password, String nombre_completo) {
		super();
		this.id = id;
		this.id_roles = id_roles;
		this.nombre_completo = nombre_completo;
		this.password = password;
		this.username = username;

	}

	public Usuario() {

	}

	public Usuario(int id_roles, String nombre_completo, String password, Usuario username) {
		super();

		this.id_roles = id_roles;
		this.nombre_completo = nombre_completo;
		this.password = password;
		this.username = username;

	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", id_roles=" + id_roles + ", nombre_completo=" + nombre_completo + ", password=" + password + ", username=" + username + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + id_roles;
		result = prime * result + ((nombre_completo == null) ? 0 : nombre_completo.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (id_roles != other.id_roles)
			return false;
		if (nombre_completo == null) {
			if (other.nombre_completo != null)
				return false;
		} else if (!nombre_completo.equals(other.nombre_completo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_roles() {
		return id_roles;
	}

	public void setId_roles(int id_roles) {
		this.id_roles = id_roles;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsername() {
		return username;
	}

	public void setUsername(Usuario username) {
		this.username = username;
	}

}
