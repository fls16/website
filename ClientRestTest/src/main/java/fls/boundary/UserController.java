package fls.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import fls.domain.UserDTO;

@Controller
public class UserController {

  @ResponseBody
  @RequestMapping(value = "/get/userDTO", method = RequestMethod.GET)
  private UserDTO getUserDTO() {
    return new UserDTO();
  }

  @ResponseBody
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  private String register(@RequestBody UserDTO userDTO) {
    String message = "default";
    if (userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
      message += "password does not match! \n";
    }
    return message;
  }

}
