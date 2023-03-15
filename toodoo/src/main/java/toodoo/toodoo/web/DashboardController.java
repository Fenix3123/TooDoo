package toodoo.toodoo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import toodoo.toodoo.domain.TooDoo;
import toodoo.toodoo.domain.User;
import toodoo.toodoo.service.TooDooService;
import toodoo.toodoo.service.UserService;

@Controller
public class DashboardController {
	@Autowired
	private UserService userService;
	@Autowired
	private TooDooService toodooService;
	@GetMapping("/dashboard")
	public String getDashboard(ModelMap model, @AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		model.put("user", user);
		model.put("toodoo", new TooDoo());
		List<TooDoo> toodooList = user.getToodoolist();
		model.addAttribute("toodoolist", toodooList);
		return "dashboard";
	}
	
	@PostMapping("/dashboard")
	public String postDashboard(@AuthenticationPrincipal User user, TooDoo toodoo) {
		user = userService.findById(user.getId());
		System.out.println(toodoo.getItem());
		toodoo.setUser(user);
		user.getToodoolist().add(toodoo);
		toodooService.saveTooDoo(toodoo);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/userView")
	public String getUserview(ModelMap model, @AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		model.put("user", user);
		return "userView";
	}
	
	@PostMapping("/userView")
	public String postUserview(@AuthenticationPrincipal User user, User userchange) {
		user = userService.findById(user.getId());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(userchange.getPassword());
		user.setUsername(userchange.getUsername());
		user.setPassword(password);
		userService.saveUser(user);
		return "redirect:/dashboard";
	}

}
