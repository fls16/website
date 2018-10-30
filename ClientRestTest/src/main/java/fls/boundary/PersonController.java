package fls.boundary;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import fls.domain.Person;
import fls.service.PersonService;

@Controller
public class PersonController {

  @Autowired
  private PersonService personService;

  @RequestMapping(value = "/persons", method = RequestMethod.GET)
  private String getPersonsOverview() {
    return "persons/overview";
  }

  @ResponseBody
  @RequestMapping(value = "/get/persons", method = RequestMethod.GET)
  private List<Person> getPersons() {
    List<Person> persons = personService.findAll();
    return persons;
  }

  @ResponseBody
  @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
  private Person getPerson(@PathVariable long id) {
    Optional<Person> person = personService.findById(id);
    return person.get();
  }

  @ResponseBody
  @RequestMapping(value = "/persons", method = RequestMethod.POST)
  private List<Person> getPersons(@RequestParam(required = false) String param) {
    List<Person> persons = personService.findAll();
    return persons;
  }

}
