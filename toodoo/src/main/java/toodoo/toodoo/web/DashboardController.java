package toodoo.toodoo.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import toodoo.toodoo.domain.User;

@Controller
public class DashboardController {
	@GetMapping("/dashboard")
	public String getDashboard(ModelMap model, @AuthenticationPrincipal User user) {
		model.put("user", user);
		return "dashboard";
	}

}
