import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {

  // private String ageLevel;
  // public static final String MAX_AGE_LEVEL = adult;
  // public static final String MED_AGE_LEVEL = young;
  // public static final String MIN_AGE_LEVEL = newborn;
  //
  // private String healthLevel;
  // public static final String MAX_HEALTH_LEVEL = healthy;
  // public static final String MED_HEALTH_LEVEL = ok;
  // public static final String MIN_HEALTH_LEVEL = ill;

  public static final String DATABASE_TYPE = "endangered";

  public EndangeredAnimal(String name) {
    this.name = name;
    this.id = id;
    // this.healthLevel = healthLevel;
    // this.healthLevel = healthLevel;
    type = DATABASE_TYPE;
  }

  // public String getHealthLevel() {
  //   return healthLevel;
  // }
  //
  // public String getAgeLevel() {
  //   return ageLevel;
  // }

  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE type = 'endangered';";
      try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).
      //.throwOnMappingFailure(false);
      executeAndFetch(EndangeredAnimal.class);

    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      EndangeredAnimal endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        //.throwOnMappingFailure(false);
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredanimal;
    }
  }


}
