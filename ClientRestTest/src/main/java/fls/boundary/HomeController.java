package fls.boundary;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fls.domain.UserDTO;

@Controller
public class HomeController {

  @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
  private String getHomepage(Model model, Locale locale) {
    model.addAttribute("userDTO", new UserDTO());
    return "home.html";
  }

}
