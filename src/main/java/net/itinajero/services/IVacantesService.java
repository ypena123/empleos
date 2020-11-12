package net.itinajero.services;

import java.util.List;

import net.itinajero.model.Vacante;

public interface IVacantesService {
	
		List<Vacante> buscarTodas();
		Vacante buscarPorId(Integer idVacante);
		void guardar (Vacante vacante); 
		List <Vacante> buscarDestacas();
		void eliminar(Integer id);
}
