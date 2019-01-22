package org.java.login.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.java.login.service.MainServices;
import org.java.login.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

	@Autowired
	MainServices mainService;

	/**
	 * Inserta Un nuevo usuario usando restfull (puro back end)
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = { RequestMethod.PUT })
	public String newUser(Model model, @RequestParam Map<String, String> requestParams) throws Exception {
		if (requestParams.get(Constantes.NAME) != null && requestParams.get(Constantes.PASS) != null) {
			String user = requestParams.get(Constantes.NAME);
			String pass = requestParams.get(Constantes.PASS);
			try {
				mainService.insertUser(user, pass);
			} catch (SQLException e) {
				throw new SQLException("Se ha producido un error al insertar");
			}
		} else {
			throw new Exception("Los parametros introducidos no son los esperados");
		}
		return "EL usuario ['" + requestParams.get(Constantes.NAME) + "'] ha sido registrado correctamente";
	}

	/**
	 * Elimina un usuario usando restfull (puro back end)
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
	public String delUser(Model model, @RequestParam Map<String, String> requestParams) throws Exception {
		if (requestParams.get(Constantes.NAME) != null) {
			String user = requestParams.get(Constantes.NAME);
			try {
				mainService.deleteUser(user);
			} catch (SQLException e) {
				throw new SQLException("Se ha producido un error al eliminar");
			}
		} else {
			throw new Exception("Los parametros introducidos no son los esperados");
		}
		return "EL usuario ['" + requestParams.get(Constantes.NAME) + "'] ha sido eliminado correctamente";
	}

	/**
	 * Consulta todos los usuarios de la base de datos
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllUser", method = { RequestMethod.GET })
	public List<String> getAllUser() throws Exception {
		try {
			return mainService.consultaAllUser();
		} catch (SQLException e) {
			throw new SQLException("Se ha producido un error al eliminar");
		}

	}

}
