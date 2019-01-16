package org.java.login.controller;

import java.sql.SQLException;
import java.util.Map;

import org.java.login.service.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	private static final String INDEX = "index";
	private static final String VAROUT = "valido";
	private static final String PASS = "psw";
	private static final String NAME = "uname";
	@Autowired
	MainServices mainService;

	/**
	 * Carga la clase por defecto, el index.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String init() {
		return INDEX;
	}

	/**
	 * valida el usuario introducido por pantalla
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String mod(Model model, @RequestParam Map<String, String> requestParams) throws SQLException {
		String user = requestParams.get(NAME);
		String pass = requestParams.get(PASS);
		
		boolean result = mainService.validarUser(user, pass);
		
		if(result) {
			model.addAttribute(VAROUT, "El usuario es correcto");
			
		}else {
			model.addAttribute(VAROUT, "El usuario es incorrecto");
		}
		return INDEX;
	}

}
