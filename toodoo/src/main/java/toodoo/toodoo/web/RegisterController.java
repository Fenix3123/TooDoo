package toodoo.toodoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import toodoo.toodoo.domain.Authorities;
import toodoo.toodoo.domain.User;
import toodoo.toodoo.service.AuthoritiesService;
import toodoo.toodoo.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	@Autowired
	AuthoritiesService authoritiesService;
	@GetMapping("/register")
	public String goToRegister(ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String postCreateUser (User user, Authorities authorities) {
		//password encoder for password saving
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		System.out.println(user);
		userService.saveUser(user);
		
		//for authorities
		authorities.setAuthority("ROLE_USER");
		authorities.setUser(user);
		authoritiesService.saveAuthorities(authorities);
		return "redirect:/logout";
	}
}
