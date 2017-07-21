import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;
import java.sql.Timestamp;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    Sighting anotherSighting = new Sighting(testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test//new test
  public void save_recordsTimeSeenInDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), "Site C", "Ranger Bill");
    testSighting.save();
    Timestamp savedTimeSeen = Sighting.find(testSighting.getId()).getTimeSeen();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedTimeSeen));
    //assertEquals(rightNow, savedTimeSeen);
  }

  @Test
  public void save_insertsObjectIntoDatabase_Sighting() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    EndangeredAnimal secondTestEndangeredAnimal = new EndangeredAnimal("Badger");
    secondTestEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
    secondTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    EndangeredAnimal secondTestEndangeredAnimal = new EndangeredAnimal("Badger");
    secondTestEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
    secondTestSighting.save();
    assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
  }

  @Test
  public void find_returnsNullWhenNoEndangeredAnimalFound_null() {
    assertTrue(EndangeredAnimal.find(999) == null);
  }

}
