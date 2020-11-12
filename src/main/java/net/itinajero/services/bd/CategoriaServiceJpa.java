package net.itinajero.services.bd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import net.itinajero.model.Categoria;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.services.ICategoriasService;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriaRepo;

	@Override
	public void guardar(Categoria categoria) {
		categoriaRepo.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriaRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriaRepo.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		Optional<Categoria> optional=categoriaRepo.findById(id);
		if(optional.isPresent()) {
			categoriaRepo.delete(optional.get());
		}
	}

	@Override
	public void modificar(Categoria categoria) {
		Optional<Categoria> optional = categoriaRepo.findById(categoria.getId());
		if(optional.isPresent()) {
			categoria = optional.get();
			categoria.setNombre(categoria.getNombre());
			categoria.setDescripcion(categoria.getDescripcion());
			categoriaRepo.save(categoria);
		}
		
	}

	@Override
	public void eliminar(Categoria categoria) {
		categoriaRepo.delete(categoria);
	}

}
