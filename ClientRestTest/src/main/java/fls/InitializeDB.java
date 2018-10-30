package fls;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import fls.domain.Person;
import fls.domain.PersonRepository;
import fls.utils.RandomString;

@Component
public class InitializeDB {

  private static final Logger LOGGER = LoggerFactory.getLogger(InitializeDB.class);

  @Autowired
  private TaskExecutor taskExecutor;

  @Autowired
  private PersonRepository personRepository;

  @PostConstruct
  public void init() {

    Person p1 = new Person("Florian", "Schmitz", 21);
    Person p2 = new Person("Timon", "Schwalbe", 23);
    Person p3 = new Person("Simon", "Eckert", 21);

    List<Person> persons = Arrays.asList(p1, p2, p3);
    personRepository.saveAll(persons);

    // Random Persons
    taskExecutor.execute(() -> {
      Person p;
      RandomString rs = new RandomString(7);
      for (int i = 0; i < 50; i++) {
        p = new Person();
        p.setFirstName(rs.nextString());
        p.setLastName(rs.nextString());
        p.setAge(new Random().nextInt(50));
        personRepository.save(p);
      }
    });

    LOGGER.info("Database initialized!");

  }

  @Bean
  public TaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(4);
    executor.setMaxPoolSize(4);
    executor.setThreadNamePrefix("default_task_executor_thread");
    executor.initialize();
    return executor;
  }

}
