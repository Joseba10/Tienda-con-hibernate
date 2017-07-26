package controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.FacturaDAO;

import com.ipartek.TIPOS.Factura;

@WebServlet("/admin/facturacrud")
public class FacturaCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO = "/WEB-INF/vistas/facturacrud.jsp";
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/facturaform.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/facturacrud";
	private static Logger log = Logger.getLogger(AltaServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = getServletContext();
		FacturaDAO facturaDAO = (FacturaDAO) application.getAttribute("compraDAO");// Si
		// entras
		// por
		// primera vez se
		// crea un objeto
		// mismo si entras
		// de nuevo

		// dal lo guarda y

		String op = request.getParameter("op");
		int id;
		if (request.getParameter("factura") == null) {

			id = 0;

		} else {
			id = Integer.parseInt(request.getParameter("factura"));
		}

		if (op == null) {

			facturaDAO.abrir();
			Factura[] facturasArr = facturaDAO.findAll();

			facturaDAO.cerrar();
			application.setAttribute("facturasArr", facturasArr);

			// request.setAttribute("factura", factura);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {

			// int numerofactura = (Integer.parseInt(request.getParameter("factura")));

			Factura factura;

			switch (op) {
			case "modificar":
			case "borrar":

				facturaDAO.abrir();
				factura = facturaDAO.findid(id);
				facturaDAO.cerrar();

				log.info(factura);
				request.setAttribute("factura", factura);
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);

				break;
			case "alta":
				log.info("factura");
				request.setAttribute("factura", new com.ipartek.TIPOS.Factura(0, 921, new java.util.Date()));
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);

				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}
}
