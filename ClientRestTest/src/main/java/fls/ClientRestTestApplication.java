package fls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientRestTestApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestTestApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ClientRestTestApplication.class, args);
    LOGGER.info("Application started!");
  }
}
