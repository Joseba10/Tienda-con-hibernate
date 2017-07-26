package controladores;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import DAO.FacturaDAO;
import DAO.FacturaDAOMySQL;
import DAO.ProductoDAO;
import DAO.ProductoDAOFactory;
import DAO.UsuarioDAO;
import DAO.UsuarioDAOMySQL;

import com.ipartek.TIPOS.Factura;
import com.ipartek.TIPOS.Producto;

public class Inicializador implements ServletContextListener {

	private static Logger log = Logger.getLogger(Inicializador.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {

		// Configurar LOG4J

		PropertyConfigurator.configure(Inicializador.class.getClassLoader().getResource("log4j.properties"));

		// Meter cosas de inicio
		UsuarioDAO usuarioDAO = new UsuarioDAOMySQL();
		ServletContext application = arg0.getServletContext();
		application.setAttribute("usuarioDAO", usuarioDAO);

		FacturaDAO compraDAO = new FacturaDAOMySQL();
		application.setAttribute("compraDAO", compraDAO);

		usuarioDAO.abrir();

		// if (usuarioDAO.findAll().length != 0) {
		//
		// usuarioDAO.deleteTableUsuarios();
		// }

		log.info("Abierta la base de datos en el Inicializador");
		// usuarioDAO.insert(new Usuario(2, "Andoni", "q", "Andoni1"));
		// usuarioDAO.insert(new Usuario(1, "admin", "admin", "admin"));
		// usuarioDAO.insert(new Usuario(2, "hodei2", "pass2", "j"));
		// usuarioDAO.insert(new Usuario(2, "joseba", "joseba", "joseba"));
		// log.info("Añadidos los usuarios");
		usuarioDAO.cerrar();

		ProductoDAO productosDAO = ProductoDAOFactory.getProductoDAO();
		productosDAO.abrir();
		// if (productosDAO.findAll().length != 0) {
		//
		// productosDAO.deleteTableProductos();
		//
		// }
		//
		// productosDAO.insert(new Producto("Manzanas", 2.0, "Manzana de Asturias", 0,
		// 17));
		// productosDAO.insert(new Producto("Tomates", 4.0, "Tomate de Asturias", 1, 21));
		// productosDAO.insert(new Producto("Patatas", 3.0, "Patatas de Alava", 2, 31));
		// productosDAO.insert(new Producto("Mandarinas", 1.0, "Mandarina de Valencia", 3,
		// 12));
		// productosDAO.insert(new Producto("Naranjas", 3.0, "Naranja de Valencia", 4,
		// 15));
		// productosDAO.insert(new Producto("Peras", 3.0, "Peras de Galicia", 5, 22));
		// productosDAO.insert(new Producto("Vinos", 1.0, "Vino de la Rioja", 6, 36));

		log.info("Añadidos los productos");
		productosDAO.cerrar();
		application.setAttribute("productoDAO", productosDAO);

		productosDAO.abrir();
		Producto[] listaproductos = productosDAO.findAll();

		productosDAO.cerrar();

		application.setAttribute("listaproductos", listaproductos);
		usuarioDAO.cerrar();

		compraDAO.abrir();

		try {

			Factura.siguiente_factura = compraDAO.getMaxId() + 1;
			System.out.println(Factura.siguiente_factura);
		} catch (Exception e) {

			log.info("Error al establecer el siguiente numero de factura");
			throw new RuntimeException("Error");
		}

		compraDAO.cerrar();

		String path = arg0.getServletContext().getContextPath();
		application.setAttribute("rutafinal", path);
	}
}
