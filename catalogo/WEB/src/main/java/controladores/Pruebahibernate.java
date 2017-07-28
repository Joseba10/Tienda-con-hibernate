package controladores;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.TIPOS.Usuario;

/**
 * Servlet implementation class Pruebahibernate
 */
public class Pruebahibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Pruebahibernate() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		EntityManager entidad = emf.createEntityManager();

		entidad.getTransaction();

		Usuario usuario = new Usuario(id_roles, nombre_completo, password, username);

	}
}
