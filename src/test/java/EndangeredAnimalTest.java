import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test//new test
  public void getSightings_retrievesAllSightingsFromDatabase_animalsList() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
    testEndangeredAnimal.save();
    Sighting firstSighting = new Sighting(testEndangeredAnimal.getId(), "Site A", "Ranger Bill");
    firstSighting.save();
    Sighting secondSighting = new Sighting(testEndangeredAnimal.getId(), "Site B", "Ranger Brad");
    secondSighting.save();
    Object[] sightings = new Object[] { firstSighting, secondSighting };
    assertTrue(testEndangeredAnimal.getSightings().containsAll(Arrays.asList(sightings)));
  }

  // @Test
  // public void getHealth_returnsHealthAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
  //   assertEquals("Healthy", testEndangeredAnimal.getHealth());
  // }

  @Test
  public void save_assignsIdAndSavesObjectToDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger");
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }

  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger");
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  // @Test
  // public void update_updatesHealthAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateHealth("ill");
  //   assertEquals("ill", EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
  // }
  //
  // @Test
  // public void update_updatesAgeAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateAge("Adult");
  //   assertEquals("Adult", EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
  // }

}
