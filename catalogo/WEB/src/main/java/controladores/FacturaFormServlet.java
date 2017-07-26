package controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.FacturaDAO;

import com.ipartek.TIPOS.Factura;
import com.ipartek.catalogo.DAL.ProductoDALException;

@WebServlet("/admin/facturaform")
public class FacturaFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProductoCrudServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("opform");

		int numero_facturas;

		if (request.getParameter("numero_factura") == null) {

			numero_facturas = 0;

		} else {

			numero_facturas = Integer.parseInt(request.getParameter("numero_factura"));
		}

		log.info(numero_facturas);
		int id_usuarios;
		if (request.getParameter("id_usuarios") == null) {

			id_usuarios = 0;

		} else {

			id_usuarios = Integer.parseInt(request.getParameter("id_usuarios"));
		}

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {

			if (request.getParameter("fecha") == null) {

				date = new Date();

			} else {

				date = parser.parse(request.getParameter("fecha"));
			}

		} catch (ParseException e) {

			e.printStackTrace();
			throw new ServletException("No se ha podido parsear la fecha", e);
		}

		int id;

		if (request.getParameter("id") == null) {

			id = 0;
		} else if (request.getParameter("id") == "") {

			id = 0;
		} else {
			log.info("coge el id del form");
			id = Integer.parseInt(request.getParameter("id"));
		}

		RequestDispatcher rutaListado = request.getRequestDispatcher(FacturaCrudServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(FacturaCrudServlet.RUTA_FORMULARIO);

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

		Factura factura = new Factura(numero_facturas, id_usuarios, date);
		factura.setId(id);
		ServletContext application = request.getServletContext();
		FacturaDAO facturadao = (FacturaDAO) application.getAttribute("compraDAO");

		switch (op) {
		case "alta":
			log.info(factura);
			facturadao.abrir();
			facturadao.insert(factura);

			application.setAttribute("factura", facturadao.findAll());
			facturadao.cerrar();
			rutaListado.forward(request, response);

			break;
		case "modificar":

			try {
				facturadao.abrir();

				facturadao.update(factura);

				application.setAttribute("factura", facturadao.findAll());
				facturadao.cerrar();
				facturadao.abrir();
				for (Factura p : facturadao.findAll()) {

					log.info(p);
				}
				facturadao.cerrar();
			} catch (ProductoDALException de) {

				request.setAttribute("factura", factura);
				rutaFormulario.forward(request, response);
				return;
			}
			rutaListado.forward(request, response);

			break;
		case "borrar":

			facturadao.abrir();
			facturadao.delete(factura);

			application.setAttribute("factura", facturadao.findAll());
			facturadao.cerrar();
			rutaListado.forward(request, response);

			break;

		}
	}
}
