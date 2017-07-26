package controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.UsuarioDAO;

import com.ipartek.TIPOS.Usuario;
import com.ipartek.catalogo.DAL.UsuarioYaExiste;

@WebServlet("/alta")
public class AltaServlet extends HttpServlet {
	/* package */static final String USUARIOS_DAO = "usuarioDAO";

	// LOG4J
	private static Logger log = Logger.getLogger(AltaServlet.class);

	private static final long serialVersionUID = 1L;

	/* package */static final String RUTA_ALTA = LoginServer.RUTA + "alta.jsp";
	/* package */static final String RUTA_ENVIO = LoginServer.RUTA + "login.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = request.getServletContext();

		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String nombre_completo = request.getParameter("nombre_completo");
		String pass2 = request.getParameter("pass2");

		// Inicio sin datos: mostrar formulario
		// Datos incorrectos: sin rellenar, límite de caracteres, no coinciden contraseñas
		// Las contraseñas deben ser iguales
		// Datos correctos: guardar

		Usuario usuario = new Usuario(1, 2, username, pass, nombre_completo);

		boolean hayDatos = username != null && pass != null && nombre_completo != null;
		boolean datosCorrectos = validarCampo(username) && validarCampo(pass) && validarCampo(nombre_completo);
		boolean passIguales = pass != null && pass.equals(pass2);

		if (hayDatos) {
			if (!datosCorrectos) {
				usuario.setErrores("Todos los campos son requeridos y con un mínimo de " + LoginServer.MINIMO_DE_CARACTERES + " caracteres");
				request.setAttribute("usuario", usuario);
			} else if (!passIguales) {
				usuario.setErrores("Las contraseñas deben ser iguales");
				request.setAttribute("usuario", usuario);
			} else {

				UsuarioDAO usuarioDAO = (UsuarioDAO) application.getAttribute(USUARIOS_DAO);

				try {
					usuarioDAO.abrir();
					usuarioDAO.insert(usuario);
					usuarioDAO.cerrar();
					log.info("Un usuario se acaba de registrar");

					request.getRequestDispatcher(RUTA_ENVIO).forward(request, response);
				} catch (UsuarioYaExiste de) {
					usuario.setUsername("");
					usuario.setErrores("El usuario ya existe. Elige otro");
					request.setAttribute("usuario", usuario);
				}

			}
		}
		request.getRequestDispatcher(RUTA_ALTA).forward(request, response);
	}

	private boolean validarCampo(String campo) {
		return campo != null && campo.length() >= LoginServer.MINIMO_DE_CARACTERES;
	}

}
