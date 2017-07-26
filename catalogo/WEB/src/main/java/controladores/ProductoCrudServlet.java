package controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.ProductoDAO;

import com.ipartek.TIPOS.Producto;

@WebServlet("/admin/productocrud")
public class ProductoCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO = "/WEB-INF/vistas/productocrud.jsp";
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/productoform.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/productocrud";
	// LOG4J
	private static Logger log = Logger.getLogger(ProductoCrudServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = request.getServletContext();
		ProductoDAO dao = (ProductoDAO) application.getAttribute("productoDAO");// Si
																				// entras
																				// por
		// primera vez se
		// crea un objeto
		// dal lo guarda y
		// siempre coge el
		// mismo si entras
		// de nuevo

		String op = request.getParameter("op");

		if (op == null) {

			// Producto[] listaproductos = (Producto[])
			// application.getAttribute("listaproductos");
			//
			// application.setAttribute("listaproductos", listaproductos);
			dao.abrir();
			Producto[] listaproductos = dao.findAll();
			dao.cerrar();

			request.setAttribute("listaproductos", listaproductos);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {

			Producto producto;
			Integer id = 0;
			switch (op) {
			case "modificar":
			case "borrar":

				if ((request.getParameter("id") != null)) {
					id = Integer.parseInt(request.getParameter("id"));
				}
				log.info(id);

				dao.abrir();
				producto = dao.findById(id);

				dao.cerrar();

				request.setAttribute("producto", producto);
				// request.getRequestDispatcher(RUTA_FORMULARIO).forward(request,
				// response);

			case "alta":
				log.info("Se ha dado un nuevo producto de alta");

				dao.abrir();
				for (Producto p : dao.findAll()) {

					log.info(p);
				}
				dao.cerrar();
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);

				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}
}
