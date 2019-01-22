package org.java.login.controller;

import java.sql.SQLException;
import java.util.List;
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
	 * Metodo que valida el usuario y la contraseña
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/", method = { RequestMethod.POST })
	public String accionV(Model model,@RequestParam Map<String, String> requestParams) throws SQLException {
		
		//Login
		if(requestParams.get(NAME)!=null && requestParams.get(PASS)!=null ) {
			
			String user = requestParams.get(NAME);
			String pass = requestParams.get(PASS);
			model=valUsu(user,pass,model);
			
		//Consulta log usuario	
		}else if(requestParams.get("logUsu")!=null) {
			String logUsu=requestParams.get("logUsu");
			model = getLogUsu(logUsu,model);
		}
		
		
		return INDEX;
	}
	
	/**
	 * 
	 * @param logUsu
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	private Model getLogUsu(String logUsu,Model model) throws SQLException {
		List<String> listaLog= mainService.consultaLog(logUsu);
		model.addAttribute("listaLog", listaLog);
		model.addAttribute("usuarioValido", logUsu);
		model.addAttribute("flagLog","S");
		model.addAttribute(VAROUT, true);
		return model;
	}
	
	/**
	 * 
	 * @param user
	 * @param pass
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	private Model valUsu(String user, String pass, Model model) throws SQLException {
		boolean result = mainService.validarUser(user, pass);
		
		if(result) {
			mainService.insertLog(user);
			model.addAttribute(VAROUT, true);
			model.addAttribute("usuarioValido", user);
			
			
		}else {
			model.addAttribute(VAROUT, false);
		}
		return model;
	}


}
