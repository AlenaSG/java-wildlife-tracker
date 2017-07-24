import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {

  private String age;
  public static final String MAX_AGE_LEVEL = "adult";
  public static final String MED_AGE_LEVEL = "young";
  public static final String MIN_AGE_LEVEL = "newborn";

  private String health;
  public static final String MAX_HEALTH_LEVEL = "healthy";
  public static final String MED_HEALTH_LEVEL = "ok";
  public static final String MIN_HEALTH_LEVEL = "ill";

  public static final String DATABASE_TYPE = "endangered";

  public EndangeredAnimal(String name) {
    this.name = name;
    this.id = id;
    type = DATABASE_TYPE;
  }

  public void setHealth(String health) {
    //validateHealth(health);
    this.health = health;
  }

  public void setAge(String age) {
    //validateAge(age);
    this.age = age;
  }

  private static void validateHealth(String health) {
    if (health != MAX_HEALTH_LEVEL &&
        health != MED_HEALTH_LEVEL &&
        health != MIN_HEALTH_LEVEL) {
        throw new IllegalArgumentException("Invalid value of health level");
    }
  }

  private static void validateAge(String age) {
    if (age != MAX_AGE_LEVEL &&
        age != MED_AGE_LEVEL &&
        age != MIN_AGE_LEVEL) {
        throw new IllegalArgumentException("Invalid value of age level");
    }
  }

  public String getType() {
    return type;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  @Override
    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO animals (name, type, age, health) VALUES (:name, :type, :age, :health);";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .addParameter("type", this.type)
          .addParameter("age", this.age)
          .addParameter("health", this.health)
          .throwOnMappingFailure(false)
          .executeUpdate()
          .getKey();
      }
    }


  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE type = 'endangered';";
      try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimal.class);

    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      EndangeredAnimal endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredanimal;
    }
  }

}
