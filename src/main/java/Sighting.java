import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
//import java.text.DateFormat;//??

public class Sighting {
  private int animal_id;
  private String location;
  private String ranger_name;
  private int id;
  private Timestamp timeseen;

  public Sighting(int animal_id, String location, String ranger_name) {
    this.animal_id = animal_id;
    this.location = location;
    this.ranger_name = ranger_name;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  public Timestamp getTimeseen(){
    return timeseen;
  }
//checked
  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName());
    }
  }
//checked
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location, ranger_name, timeseen) VALUES (:animal_id, :location, :ranger_name, now());";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location", this.location)
        .addParameter("ranger_name", this.ranger_name)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }
  //new
  public static Sighting findbyTimeSeen(Timestamp timeseen) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE timeseen = :timeseen;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("timeseen", timeseen)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }
//new - test not passing
  public static Sighting findbyLatest(Timestamp timeseen) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT MAX (timeseen) FROM sightings WHERE timeseen = :timeseen;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("timeseen", timeseen)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public void update(String ranger_name) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE sightings SET ranger_name = :ranger_name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("ranger_name", ranger_name)
      .addParameter("id", id)
      .executeUpdate();
  }
}

public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM sightings WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }

}
}
