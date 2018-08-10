package com.metacube.sageclarity.predictable.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metacube.sageclarity.predictable.entity.Role;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.service.UserService;

@RestController
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect("/predictable-client/index.html");
	}*/

	@PostConstruct
	public void init() {

		try {
			User user = new User("User", "user", passwordEncoder.encode("password"), true);
			List<Role> roles1 = new ArrayList<>();
			roles1.add(new Role("ROLE_USER"));
			user.setRoles(roles1);
			userService.saveUser(user);
			User admin = new User("Admin", "admin", passwordEncoder.encode("password"), true);
			List<Role> roles2 = new ArrayList<>();
			roles2.add(new Role("ROLE_ADMIN"));
			admin.setRoles(roles2);
			userService.saveUser(admin);
		} catch (ApplicationLevelException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/resource")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    return model;
	  }

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	/*@GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}*/
}
