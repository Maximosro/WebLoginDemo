package org.java.login.controller;

import java.util.List;
import java.util.Map;

import org.java.login.model.LogLine;
import org.java.login.repository.LogLineDao;
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
public class RestJpaController {

	@Autowired
	MainServices mainService;

	@Autowired
	LogLineDao logLineDao;

	/**
	 * Inserta Un nuevo usuario usando restfull (puro back end)
	 * 
	 * @param model
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/userlog", method = { RequestMethod.GET })
	public List<LogLine> newUser(Model model, @RequestParam Map<String, String> requestParams) throws Exception {
		List<LogLine> logUser = null;
		if (requestParams.get(Constantes.USERNAME) != null) {

			logUser = (List<LogLine>) logLineDao.findAll();//logLineDao.findByNameLike(requestParams.get(Constantes.USERNAME));
			

		} else {
			throw new Exception("Los parametros introducidos no son los esperados");
		}
		return logUser;
	}

}
