package com.bookcrossing.api.util;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {

  private static final String IMAGE_VERSION = "postgres:12.0";

  private static TestPostgreSQLContainer container;

  private TestPostgreSQLContainer() {
    super(IMAGE_VERSION);
  }

  public static TestPostgreSQLContainer getInstance() {
    if (container == null) {
      container = new TestPostgreSQLContainer();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();

    // Workaround to delete connection string prefix that was hardcoded into the configuration
    String fixedConnectionString = container.getJdbcUrl().replace("jdbc:postgresql://", "");

    System.setProperty("POSTGRES_DB_HOST", fixedConnectionString);
    System.setProperty("POSTGRES_USER", container.getUsername());
    System.setProperty("POSTGRES_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
    // do nothing, JVM handles shut down
  }
}

