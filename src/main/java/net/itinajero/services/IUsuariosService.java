package net.itinajero.services;

import java.util.List;
import net.itinajero.model.Usuario;

public interface IUsuariosService {

	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
}


