package net.itinajero.services.bd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.itinajero.model.Vacante;
import net.itinajero.repository.VacantesRepository;
import net.itinajero.services.IVacantesService;

@Service
@Primary
public class VacanteServiceJpa implements IVacantesService {
	
	@Autowired
	VacantesRepository vacanteRepo;

	@Override
	public List<Vacante> buscarTodas() {
		return vacanteRepo.findAll();
		
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional=vacanteRepo.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacanteRepo.save(vacante);
	}

	@Override
	public List<Vacante> buscarDestacas() {
		return vacanteRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
		
	}

	@Override
	public void eliminar(Integer id) {
		vacanteRepo.deleteById(id);
		}

}
