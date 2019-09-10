package ca.ulaval.glo4002.organisation;

import org.springframework.boot.SpringApplication;

public class OrganisationServer implements Runnable {
  private String[] args;

  public static void main(String[] args) throws Exception {
    new OrganisationServer(args).run();
  }

  public OrganisationServer(String[] args) {
    this.args = args;
  }

  @Override
  public void run() {
    SpringApplication.run(OrganisationSpringApplication.class, args);
  }
}
