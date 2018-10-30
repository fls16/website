package fls.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fls.domain.Person;
import fls.domain.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Override
  public Optional<Person> findById(long id) {
    return personRepository.findById(id);
  }

}
