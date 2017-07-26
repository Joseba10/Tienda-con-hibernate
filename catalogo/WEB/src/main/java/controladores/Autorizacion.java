package controladores;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ipartek.TIPOS.Usuario;

public class Autorizacion implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();

		Usuario usuario = null;

		if (session != null) {
			usuario = (Usuario) session.getAttribute("usuario");
		}

		boolean isAdmin = usuario.getId_roles() == 1;

		// if (!nuevoUsuario) {
		//
		// esAdmin = usuario.isAdmin();
		//
		// }
		//
		if (!isAdmin) {

			(req.getRequestDispatcher("/loginserver")).forward(request, response);
		}

		else {

			chain.doFilter(request, response);

		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
