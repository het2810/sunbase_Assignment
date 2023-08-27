package SunBaseData.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	@GetMapping("/")
	public String onStart() {
		return "Login";
	}
	@PostMapping("/login")
    public String login(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Here, you can implement your authentication logic.
        // Check if loginId and password are correct.
		
		if(loginId == "test@sunbasedata.com" && password == "Test@123") {
			return "redirect:/customers/create";
		}
		
         else {
            // Authentication failed. Add an error message and redirect back to the login page.
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials. Please try again.");
            return "redirect:/login";
        }
    }
}
