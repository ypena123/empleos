package net.itinajero.controller;

import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.itinajero.model.Perfil;
import net.itinajero.model.Usuario;
import net.itinajero.model.Vacante;
import net.itinajero.services.ICategoriasService;
import net.itinajero.services.IUsuariosService;
import net.itinajero.services.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategoria;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
   	private IUsuariosService serviceUsuarios;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario, Model model) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		usuario.setEstatus(1);
		usuario.setFechaRegistro(new Date());
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
				
		serviceUsuarios.guardar(usuario);	
		attributes.addFlashAttribute("msg", "El usuario se ha guardado exitosamente");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero  de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarDestacas());
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
	}
	
}
