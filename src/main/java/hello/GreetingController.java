package hello;

import hello.dao.GreetingsDAO;
import hello.dto.FormInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class GreetingController {

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	public String greeting(
			@Valid FormInput formInput,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "index";
		}
		model.addAttribute("name", GreetingsDAO.getGreetingForId(formInput.getId()).getName());
		//model.addAttribute("name", formInput.getId());
		return "greeting";
	}

	@RequestMapping(value = {"/", "/index"})
	public String index(Model model) {
		model.addAttribute("formInput", new FormInput());
		return "index";
	}
}
