package net.itinajero.services.bd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.model.Usuario;
import net.itinajero.repository.UsuariosRepository;
import net.itinajero.services.IUsuariosService;

@Service
public class UsuarioServiceJpa implements IUsuariosService {

	@Autowired
	UsuariosRepository UsuarioRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		UsuarioRepo.save(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		UsuarioRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return UsuarioRepo.findAll();
	}

}
