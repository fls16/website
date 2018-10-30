package fls.service;

import java.util.List;
import java.util.Optional;
import fls.domain.Person;

public interface PersonService {

  List<Person> findAll();

  Optional<Person> findById(long id);

}
