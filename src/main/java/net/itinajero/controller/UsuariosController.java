package net.itinajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Usuario;
import net.itinajero.services.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	IUsuariosService repoUsuario;
    
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Usuario> usuarios=repoUsuario.buscarTodos();
    	model.addAttribute("usuario", usuarios);
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		repoUsuario.eliminar(idUsuario);	
		attributes.addFlashAttribute("msg2", "El usuario se ha eliminado exitosamente");
		return "redirect:/usuarios/index";
	}
}
