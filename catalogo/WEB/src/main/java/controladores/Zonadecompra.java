package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FacturaDAO;
import DAO.ProductoDAO;

import com.ipartek.TIPOS.Carrito;
import com.ipartek.TIPOS.Factura;
import com.ipartek.TIPOS.Producto;
import com.ipartek.TIPOS.Usuario;

@WebServlet("/Zonadecompra")
public class Zonadecompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");

		ServletContext application = request.getServletContext();

		FacturaDAO compraDAO = (FacturaDAO) application.getAttribute("compraDAO");

		ProductoDAO productodao = (ProductoDAO) application.getAttribute("productoDAO");
		String[] productos = request.getParameterValues("productos");

		String[] cantidades = request.getParameterValues("cantidad");

		ArrayList<Producto> productosarray = new ArrayList<>();

		// Recoger las opciones que esten seleccionadas
		// int cantidad = 1;
		// if (productos != null) {
		// for (String producto : productos) {
		//
		// if (request.getParameter("cantidad-" + producto) == "") {
		//
		// cantidad = 1;
		// }
		//
		// else {
		// cantidad = Integer.parseInt(request.getParameter("cantidad"));
		//
		// System.out.println("ID: " + producto + ", cantidad: " + cantidad);
		// }
		// }
		// }
		System.out.println("productosarray: " + productosarray);
		// Recojo la sesion por lo que puedo coger toda la informacion que
		// contenga
		HttpSession session = request.getSession();
		session.setAttribute("listaproductos", productos);

		productosarray = (ArrayList<Producto>) session.getAttribute("productosarray");

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		TreeMap<Integer, Producto> productosCarrito = (TreeMap<Integer, Producto>) session.getAttribute("productosCarrito");

		if (op == null) {

			System.out.println(productos.toString());

		} else {

			switch (op) { // La primera vez que entre ira a la zona del
							// formulario
			case "primeravez":

				productodao.abrir();
				Producto[] arrayproductos = productodao.findAll();
				productodao.cerrar();
				request.setAttribute("arrayproductos", arrayproductos);
				request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
				break;

			case "completado":

				Enumeration<String> nombres = request.getParameterNames();

				productodao.abrir();

				if (productosCarrito == null) {

					productosCarrito = new TreeMap<Integer, Producto>();
				}

				while (nombres.hasMoreElements()) {
					String idStr = nombres.nextElement();
					int cantidad;
					try {

						cantidad = Integer.parseInt(request.getParameter(idStr));
					} catch (Exception e) {

						cantidad = 0;
					}
					System.out.println(idStr);
					System.out.println(request.getParameter(idStr));
					if (cantidad != 0) {

						int id = Integer.parseInt(idStr);

						Producto p = productodao.findById(id);

						int cantidadRepetida = 0;
						if (productosCarrito.containsKey(p.getId())) {

							/*
							 * Hacer TreeMap para sustituirlo de esta manera podria coger
							 * los atributos,en este caso cantidad
							 */
							cantidadRepetida = productosCarrito.get(p.getId()).getCantidad();

							// for (Producto productoRepetido :
							// productosCarrito.values())
							// {
							//
							// if (productoRepetido.getId() == p.getId()) {
							// cantidadRepetida =
							// productoRepetido.getCantidad();
							//
							// }
							//
							// }

							p.setCantidad(cantidad + cantidadRepetida);

						} else {
							p.setCantidad(cantidad);
						}

						if (p.getCantidad() <= 0) {

							productosCarrito.remove(p.getId(), p);
						} else {

							productosCarrito.put(p.getId(), p);
						}

						p.setCantidad(cantidad);
						cantidad = 0;
					}
				}
				productodao.cerrar();

				System.out.println(productosCarrito);
				session.setAttribute("productosCarrito", productosCarrito);

				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);
				break;

			case "confirmado": {

				int id = ((Usuario) session.getAttribute("usuario")).getId();
				Factura factura = new Factura(id, new Date());

				compraDAO.abrir();
				productodao.abrir();
				Producto producto;

				int id_factura = compraDAO.insert(factura);

				for (Producto p : productosCarrito.values()) {

					compraDAO.insertFacturaProducto(id_factura, p.getId(), p.getCantidad());
					producto = productodao.findById(p.getId());
					producto.setCantidad(producto.getCantidad() - p.getCantidad());
					productodao.update(producto);

				}

				application.setAttribute("listaproductos", productodao.findAll());
				productodao.cerrar();
				compraDAO.cerrar();

			}
			session.setAttribute("productosCarrito", new TreeMap<Integer,Producto>());
				request.getRequestDispatcher("/WEB-INF/vistas/productocrudusuario.jsp").forward(request, response);
				break;
			case "finalizado": {

				request.getRequestDispatcher("/WEB-INF/vistas/compraconfirmada.jsp").forward(request, response);

				break;
			}
			default:
				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);

				break;
			}
		}
	}
}
