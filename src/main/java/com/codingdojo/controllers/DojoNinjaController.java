package com.codingdojo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.models.Dojo;
import com.codingdojo.models.Ninja;
import com.codingdojo.services.DojoService;
import com.codingdojo.services.NinjaService;

@Controller
public class DojoNinjaController {
	
	private final DojoService dojoService;
	private final NinjaService ninjaService;
	
	public DojoNinjaController(DojoService dojoService, NinjaService ninjaService) {
		this.dojoService=dojoService;
		this.ninjaService=ninjaService;
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/dojos/new";
	}
	
	@RequestMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojo")Dojo dojo) {
		return "index.jsp";
	}
	
	@RequestMapping(value="/dojos/new", method=RequestMethod.POST)
	public String createDojo(@ModelAttribute("dojo")Dojo dojo) {
		dojoService.createDojo(dojo);
		return "redirect:/dojos/new";
	}
	
	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja")Ninja ninja, Model model) {
		List<Dojo> Dojos = dojoService.getAllDojos();
		model.addAttribute("dojos", Dojos);
		return "newNinja.jsp";
	}
	

	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
	public String createNinja(@ModelAttribute("ninja")Ninja ninja) {
		ninjaService.createNinja(ninja);
		return "redirect:/ninjas/new";
	}
	
	@RequestMapping("dojos/{id}")
	public String displayinfo(@PathVariable("id")Long id, Model model) {
		Dojo dojo = dojoService.findDojoById(id);
		model.addAttribute("dojo",dojo);
		model.addAttribute("ninjas", dojo.getNinjas());
		return "dojoPage.jsp";
	}
	

}
