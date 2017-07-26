package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.ProductoDAO;

import com.ipartek.TIPOS.Producto;
import com.ipartek.catalogo.DAL.ProductoDALException;

@WebServlet("/admin/productoform")
public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProductoCrudServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("opform");
		String nombre = request.getParameter("nombre");
		int id;
		String descripcion = request.getParameter("descripcion");
		double precio;
		int imagen = 0;
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));

		if (request.getParameter("imagen") == null) {

			imagen = 0;
		} else if (request.getParameter("imagen") == "") {

			imagen = 0;
		} else {

			imagen = Integer.parseInt(request.getParameter("imagen"));
		}

		if (request.getParameter("id") == null) {

			id = 0;
		} else if (request.getParameter("id") == "") {

			id = 0;
		} else {
			id = Integer.parseInt(request.getParameter("id"));
		}

		if (request.getParameter("precio") == null) {

			precio = 0;
		} else if (request.getParameter("precio") == "") {

			precio = 0;
		} else {

			precio = Double.parseDouble(request.getParameter("precio"));
		}

		RequestDispatcher rutaListado = request.getRequestDispatcher(ProductoCrudServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(ProductoCrudServlet.RUTA_FORMULARIO);

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

		Producto producto = new Producto(nombre, precio, descripcion, imagen, cantidad);
		producto.setId(id);
		ServletContext application = request.getServletContext();
		ProductoDAO dao = (ProductoDAO) application.getAttribute("productoDAO");

		switch (op) {
		case "alta":

			if (!dao.validar(producto)) {
				dao.abrir();
				dao.insert(producto);

				application.setAttribute("listaproductos", dao.findAll());
				dao.cerrar();
				rutaListado.forward(request, response);
			} else {
				producto.setErrores("El producto ya existe");
				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);
			}

			break;
		case "modificar":

			try {
				dao.abrir();
				dao.update(producto);

				application.setAttribute("listaproductos", dao.findAll());
				dao.cerrar();
				dao.abrir();
				for (Producto p : dao.findAll()) {

					log.info(p);
				}
				dao.cerrar();
			} catch (ProductoDALException de) {
				producto.setErrores(de.getMessage());
				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);
				return;
			}
			rutaListado.forward(request, response);

			break;
		case "borrar":

			dao.abrir();
			dao.delete(producto);

			application.setAttribute("listaproductos", dao.findAll());
			dao.cerrar();
			rutaListado.forward(request, response);

			break;

		}
	}
}
