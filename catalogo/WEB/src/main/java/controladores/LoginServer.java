package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DAO.ProductoDAO;
import DAO.UsuarioDAO;

import com.ipartek.TIPOS.Producto;
import com.ipartek.TIPOS.Usuario;

@WebServlet("/loginserver")
public class LoginServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = RUTA + "productocrudusuario.jsp";
	private static final String RUTA_ADMIN = RUTA + "productocrud.jsp";

	private static final String RUTA_LOGIN = RUTA + "login.jsp";
	public static final int TIEMPO_INACTIVIDAD = 30 * 60;
	/* Package */static final int MINIMO_DE_CARACTERES = 4;

	// LOG4J
	private static Logger log = Logger.getLogger(LoginServer.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Recoger datos de vistas
		String username = request.getParameter("username");
		String nombre_completo = request.getParameter("nombre_completo");
		String password = request.getParameter("password");
		String opcion = request.getParameter("opcion");
		// Crear modelos en base a los datos
		Usuario usuario = new Usuario(0, 2, username, password, nombre_completo);
		usuario.setUsername(username);
		usuario.setPassword(password);

		// Llamada a la logica de negocio
		ServletContext application = getServletContext();
		// Recoge datos
		UsuarioDAO usuarioDAO = (UsuarioDAO) application.getAttribute("usuarioDAO");

		ProductoDAO productoDAO = (ProductoDAO) application.getAttribute("productoDAO");
		// Si no existe el dato se crea

		Producto[] listaproductos = (Producto[]) application.getAttribute("listaproductos");

		// Solo para crear una base de datos falsa con el contenido de un
		// usuario
		// "joseba","clemente"

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);

		// for (Cookie cookie : request.getCookies()) {
		//
		// if ("JSESSIONID".equals(cookie.getName())) {
		//
		// cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		// response.addCookie(cookie);
		// }
		// }

		// session.setAttribute("listaproductos", listaproductos);
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);
		// ESTADOS
		usuarioDAO.abrir();
		boolean esValido = usuarioDAO.validar(usuario);

		usuarioDAO.cerrar();

		boolean sinParametros = usuario.getUsername() == null;

		boolean esUsuarioRegistrado = request.getSession().getAttribute("usuario") != null;

		boolean quiereSalir = "logout".equals(opcion);

		boolean nombreValido = usuario.getUsername() != null && usuario.getUsername().length() >= MINIMO_DE_CARACTERES;

		boolean passValido = usuario.getPassword() != null && usuario.getPassword().length() >= MINIMO_DE_CARACTERES;

		boolean esadmin = ("admin").equals(usuario.getUsername()) && ("admin").equals(usuario.getPassword());
		// Redirigir a una nueva vista

		if (quiereSalir) {
			log.info("Un usuario ha abandonado la pagina");
			session.invalidate();

			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		}

		else if (esadmin) {

			usuarioDAO.abrir();

			usuario = usuarioDAO.findByName(usuario.getUsername());
			log.info(usuario.getId());
			log.info(usuario.getUsername());
			log.info(usuario.getId_roles());
			usuarioDAO.cerrar();

			session.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_ADMIN).forward(request, response);
			log.info("Un usuario se acaba de logear con el usuario Admin");

		} else if (esUsuarioRegistrado) {
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);

		} else if (sinParametros) {
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		}

		else if (!nombreValido || !passValido) {

			usuario.setErrores("El nombre y las pass deben tener como minimo " + MINIMO_DE_CARACTERES + " y son ambos requeridos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);

		}

		else if (esValido) {

			usuarioDAO.abrir();

			usuario = usuarioDAO.findByName(usuario.getUsername());
			log.info(usuario.getId());
			log.info(usuario.getUsername());
			log.info(usuario.getId_roles());
			usuarioDAO.cerrar();

			session.setAttribute("usuario", usuario);

			ArrayList<Producto> productosarray = new ArrayList<>();
			session.setAttribute("productosarray", productosarray);

			// response.sendRedirect("principal.jsp");
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);

			log.info("El usuario " + usuario.getUsername() + " se acaba de logear");
		}

		else {

			usuario.setErrores("El usuario y contraseña introducidos no son validos");
			request.setAttribute("usuario", usuario);

			// lleva el contenido de usuario a la pagina login
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		}
	}
}
