package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOException;
import DAO.UsuarioDAO;

import com.ipartek.TIPOS.Usuario;

@WebServlet("/admin/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("opform");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pass2 = request.getParameter("pass2");

		int id_roles = 0;
		if (request.getParameter("id_roles") != null) {
			id_roles = Integer.parseInt(request.getParameter("id_roles"));
		}

		String nombre_completo = request.getParameter("nombre_completo");

		RequestDispatcher rutaListado = request.getRequestDispatcher(UsuarioCrudServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(UsuarioCrudServlet.RUTA_FORMULARIO);

		// response.setContentType("text/plain");
		// PrintWriter out = response.getWriter();
		// out.println(op);
		// out.println(nombre);
		// out.println(pass);
		// out.println(pass2);

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		Usuario usuario = new Usuario(0, id_roles, username, password, nombre_completo);

		ServletContext application = getServletContext();
		UsuarioDAO usuarioDAO = (UsuarioDAO) application.getAttribute("usuarioDAO");

		switch (op) {
		case "alta":
			if (password.equals(pass2)) {
				usuarioDAO.abrir();
				usuarioDAO.insert(usuario);
				usuarioDAO.cerrar();
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "modificar":
			if (password.equals(pass2)) {
				try {
					usuarioDAO.abrir();

					usuarioDAO.update(usuario);

					usuarioDAO.cerrar();

				} catch (DAOException de) {
					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "borrar":

			usuarioDAO.abrir();

			usuarioDAO.delete(usuario);

			usuarioDAO.cerrar();
			rutaListado.forward(request, response);

			break;
		}
	}
}
