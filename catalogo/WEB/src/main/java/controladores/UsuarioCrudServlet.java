package controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UsuarioDAO;

import com.ipartek.TIPOS.Usuario;

@WebServlet("/admin/usuariocrud")
public class UsuarioCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/usuariocrud";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = getServletContext();
		UsuarioDAO usuarioDAO = (UsuarioDAO) application.getAttribute("usuarioDAO");// Si
		// entras
		// por
		// primera vez se
		// crea un objeto
		// mismo si entras
		// de nuevo

		// dal lo guarda y

		String op = request.getParameter("op");

		if (op == null) {

			usuarioDAO.abrir();
			Usuario[] usuarios = usuarioDAO.findAll();
			usuarioDAO.cerrar();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {

			String username = (request.getParameter("username"));

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":

				usuarioDAO.abrir();
				usuario = usuarioDAO.findByName(username);
				usuarioDAO.cerrar();
				request.setAttribute("usuario", usuario);

			case "alta":

				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}
}
