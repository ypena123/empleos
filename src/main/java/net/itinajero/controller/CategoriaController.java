package net.itinajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Categoria;
import net.itinajero.model.Vacante;
import net.itinajero.services.ICategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	ICategoriasService serviceCategoria;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> listaCategoria=serviceCategoria.buscarTodas();
		model.addAttribute("categoria", listaCategoria);
	return "categorias/listCategorias";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categorias/formCategoria";
	}
	
	@PostMapping("/save")
	public String guardar(Categoria categoria, Model model, BindingResult result, RedirectAttributes attribut) {
		boolean create = categoria.getId()!=null?false:true;
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error " + error.getDefaultMessage());
			}
			return "redirect:/categorias/create";
		}
		serviceCategoria.guardar(categoria);
		if (create) {
			attribut.addFlashAttribute("msj", "Registro guardado exitosamente");
		} else {
			attribut.addFlashAttribute("msj3", "El registro ha sido modificado exitosamente");
		}
		return "redirect:/categorias/index";

	}
	
	
	@GetMapping("/delete")
	public String eliminar (@RequestParam("id") int idCategoria, Model model, RedirectAttributes attribut) {
		try {
			serviceCategoria.eliminar(idCategoria);
		} catch (Exception e) {	
			attribut.addFlashAttribute("msj2", "El registro no puede ser eliminado");
		}
		model.addAttribute("idCategoria", idCategoria);
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/modificar/{id}")
	public String update(@PathVariable("id") int idCategoria, Model model, RedirectAttributes attribut) {
		Categoria categoria = serviceCategoria.buscarPorId(idCategoria);
		if (categoria != null) {
			model.addAttribute("categoria", categoria);
		}else {
			model.addAttribute("categoria", new Categoria());
		}
		return "categorias/formCategoria";
	}
}
