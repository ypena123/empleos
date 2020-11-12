package net.itinajero.services;

import java.util.List;
import net.itinajero.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	
	void eliminar(Integer id);
	void eliminar(Categoria categoria);
	void modificar(Categoria categoria);
}
