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

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger");
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }


  @Test//new
  public void delete_deletesAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox");
    firstEndangeredAnimal.save();
    int firstEndangeredAnimalId = firstEndangeredAnimal.getId();
    firstEndangeredAnimal.delete();
    assertEquals(null, EndangeredAnimal.find(firstEndangeredAnimalId));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Deer");
    EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal("Deer");
    assertTrue(firstEndangeredAnimal.equals(anotherEndangeredAnimal));
  }


  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox");
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger");
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  @Test
  public void find_returnsNullWhenNoEndangeredAnimalFound_null() {
    assertTrue(EndangeredAnimal.find(999) == null);
  }

  @Test
  public void getName_EndangeredAnimalInstantiatesWithName_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    assertEquals("Deer", testEndangeredAnimal.getName());
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

  @Test
  public void save_assignsIdAndSavesObjectToDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
  }

  @Test//new test
  public void EndangeredAnimal_instantiatesWithAType() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
    testEndangeredAnimal.save();
    String savedType = EndangeredAnimal.find(testEndangeredAnimal.getId()).getType();
    assertEquals("endangered", savedType);
  }

  @Test//new
 public void updateName_updatesAnimalName_true() {
   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
   testEndangeredAnimal.save();
   testEndangeredAnimal.updateName("Badger");
   assertEquals("Badger", EndangeredAnimal.find(testEndangeredAnimal.getId()).getName());
 }

 @Test
 public void saveSavesHealth_true() {
  EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
  testEndangeredAnimal.setHealth(EndangeredAnimal.MED_HEALTH_LEVEL);
  testEndangeredAnimal.save();
  String savedHealth = EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth();
  assertEquals(EndangeredAnimal.MED_HEALTH_LEVEL, savedHealth);
 }

 @Test
 public void saveSavesAge_true() {
  EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
  testEndangeredAnimal.setAge(EndangeredAnimal.MED_AGE_LEVEL);
  testEndangeredAnimal.save();
  String savedAge = EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge();
  assertEquals(EndangeredAnimal.MED_AGE_LEVEL, savedAge);
 }
  // @Test//new test
  // public void EndangeredAnimal_instantiatesWithAHelthLevel() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer");
  //   testEndangeredAnimal.getHealth("ok");
  //   testEndangeredAnimal.save();
  //   String savedHealth = EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth();
  //   assertEquals("ok", savedHealth);
  // }


  // @Test
  // public void getHealth_returnsHealthAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
  //   assertEquals("Healthy", testEndangeredAnimal.getHealth());
  // }

  // @Test
  // public void update_updatesHealthAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateHealth("ill");
  //   assertEquals("ill", EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
  // }

  // @Test
  // public void update_updatesAgeAttribute_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox");
  //   testEndangeredAnimal.save();
  //   testEndangeredAnimal.updateAge("Adult");
  //   assertEquals("Adult", EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
  // }



}
