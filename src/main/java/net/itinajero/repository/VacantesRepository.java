package net.itinajero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import net.itinajero.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	//Metodo para buscar por estatus en BD
	List<Vacante> findByEstatus(String estatus);
	
	//Metodo para buscar por destacado, estatus, ordenados por ID de manera descendente
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	//Metodo para buscar entre rango de salario ordenado por salario de manera descendente
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	
	//Metodo para buscar por varios estatus
	List<Vacante> findByEstatusIn(String estatus[]);
	
	List<Vacante> findByNombreLike(String like);

}
