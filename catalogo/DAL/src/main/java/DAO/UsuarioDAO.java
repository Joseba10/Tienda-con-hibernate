package DAO;

import com.ipartek.TIPOS.Usuario;

public interface UsuarioDAO extends IpartekDAO {

	public Usuario[] findAll(); // Select

	public Usuario findById(int id);

	public int insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(String username);

	public void cerrar();

	public boolean validar(Usuario usuario);

	public void deleteTableUsuarios();

	public Usuario findByName(String username);

}
