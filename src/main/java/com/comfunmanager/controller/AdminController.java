package com.comfunmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.repository.ServerinfoRepository;
import com.comfunmanager.utils.DateTimeUtils;

/**
 *
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	private ServerinfoRepository serverinfoRepository;

	private String strToken;
	
	@Autowired
	public AdminController(ServerinfoRepository serverinfoRepository) {

		this.serverinfoRepository = serverinfoRepository;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/")
	public String adminIndex(Model model) {
		model.addAttribute("dashboard", true);
		model.addAttribute("serverscount", serverinfoRepository.count());
		
		LOGGER.info("access admin /");

		return "admin/index";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user")
	public String adminUser(Model model) {
		model.addAttribute("user", true);
		return "admin/user";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/serverinfo")
	public String adminserverinfo(Model model) {
		model.addAttribute("serverinfo", true);
		Iterable<Serverinfo> list = serverinfoRepository.findAll();
		model.addAttribute("serverinfolist", list);
	
		
		return "admin/serverinfo";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/serverinfo/add")
	public String serverinfoAdd(Model model) {
		Serverinfo info = new Serverinfo();
		info.setServerid(DateTimeUtils.createNowTimeId());
		info.setCreatetime(DateTimeUtils.getNowDateTime());
		model.addAttribute("serverinfoAdd", info);
		return "admin/serverinfoAdd";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/serverinfo/edit")
	public String serverinfoEdit(Model model, @RequestParam Long id) {
		model.addAttribute("serverinfoEdit", serverinfoRepository.findOne(id));
		return "admin/serverinfoEdit";
	}

	/**
	 * 
	 * @param stores
	 * @return
	 */
	@PostMapping("/admin/serverinfo/edit")
	public String serverinfoSubmit(@ModelAttribute Serverinfo info) {
		info.setUpdatetime(DateTimeUtils.getNowDateTime());
		
		serverinfoRepository.save(info);
		return "redirect:/admin/serverinfo";
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/serverinfo/del")
	public String delServerinfo(Model model, @RequestParam Long id) {
		serverinfoRepository.delete(id);
		return "redirect:/admin/serverinfo";
	}

	
	/**
	 *  configuration
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/configuration")
	public String configuration(Model model) {
		return "admin/configuration";
	}

}
