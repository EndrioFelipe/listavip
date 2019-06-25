package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoRepository repository;
	
	@RequestMapping("/")
	public String index() {
		return "index"; 
	}
	
	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		
		Iterable<Convidado> convidados = repository.findAll();
		
		model.addAttribute("convidados", convidados);
		
		
		
		return "listaconvidados";
	}
	
	@RequestMapping(value= "salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@RequestParam("nome") String nome, @RequestParam("email") String email, 
	                   @RequestParam("telefone") String telefone){

	    Convidado novoConvidado = new Convidado(nome, email, telefone);
	    repository.save(novoConvidado);
	    
	    Iterable<Convidado> convidados = repository.findAll();
	    ModelAndView model = new ModelAndView("redirect:listaconvidados");
	    model.addObject("convidados", convidados);

	    return model;
	}
}
