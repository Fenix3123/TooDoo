package toodoo.toodoo.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import toodoo.toodoo.domain.TooDoo;
import toodoo.toodoo.domain.User;
import toodoo.toodoo.service.TooDooService;
import toodoo.toodoo.service.UserService;

@Controller
public class TooDooViewController {
	@Autowired
	private TooDooService toodooservice;
	@Autowired
	private UserService userService;
	@GetMapping("/toodoo/{toodooid}")
	public String getTooDoo(ModelMap model,@AuthenticationPrincipal User user,@PathVariable Long toodooid) {
		TooDoo obj = toodooservice.getById(toodooid);
		model.put("toodooobj", obj);
		
		return "toodooview";
	}
	@PostMapping("/toodoo/{toodooid}")
	public String postTooDoo(@AuthenticationPrincipal User user,@PathVariable Long toodooid, TooDoo toodoo) {
		TooDoo originaltoodoo = toodooservice.getById(toodooid);
		originaltoodoo.setItem(toodoo.getItem());
		toodooservice.saveTooDoo(originaltoodoo);
		
		return "redirect:/toodoo/"+toodooid;
	}
	@PostMapping("/toodoo/delete/{toodooid}")
	public String deleteTooDoo(@AuthenticationPrincipal User user, @PathVariable Long toodooid){
		User user2 = userService.findById(user.getId());
		TooDoo originaltoodoo = toodooservice.getById(toodooid);
		user2.setToodoolist(user2.getToodoolist().stream()
				   .filter(toodootoo ->{
					   String item1 = String.valueOf(toodootoo.getItem());
					   String item2 = String.valueOf(originaltoodoo.getItem());
					   return !item1.equals(item2);
				   })
				   .collect(Collectors.toList()));
		userService.saveUser(user2);
		toodooservice.delete(originaltoodoo);
		return "redirect:/dashboard";
	}
}
