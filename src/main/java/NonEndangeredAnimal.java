import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NonEndangeredAnimal extends Animal {
//private int fireLevel;
//public static final String MAX_FIRE_LEVEL = 10;
public static final String DATABASE_TYPE = "nonendangered";


  public NonEndangeredAnimal(String name) {
    this.name = name;
    this.id = id;
    type = DATABASE_TYPE;
  }


  public static List<NonEndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE type = 'nonendangered';";
      try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).
      //.throwOnMappingFailure(false);
      executeAndFetch(NonEndangeredAnimal.class);
    }
  }

  public static NonEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      NonEndangeredAnimal nonendangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        //.throwOnMappingFailure(false);
        .executeAndFetchFirst(NonEndangeredAnimal.class);
      return nonendangeredanimal;
    }
  }






}
