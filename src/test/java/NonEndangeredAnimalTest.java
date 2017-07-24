import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NonEndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void NonEndangeredAnimal_instantiatesCorrectly_false() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    assertEquals(true, testNonEndangeredAnimal instanceof NonEndangeredAnimal);
  }

  @Test//new test
  public void getSightings_retrievesAllSightingsFromDatabase_animalsList() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox");
    testNonEndangeredAnimal.save();
    Sighting firstSighting = new Sighting(testNonEndangeredAnimal.getId(), "Site A", "Ranger Bill");
    firstSighting.save();
    Sighting secondSighting = new Sighting(testNonEndangeredAnimal.getId(), "Site B", "Ranger Brad");
    secondSighting.save();
    Object[] sightings = new Object[] { firstSighting, secondSighting };
    assertTrue(testNonEndangeredAnimal.getSightings().containsAll(Arrays.asList(sightings)));
  }

  @Test
  public void getName_NonEndangeredAnimalInstantiatesWithName_Deer() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    assertEquals("Deer", testNonEndangeredAnimal.getName());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    NonEndangeredAnimal anotherNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    assertTrue(firstNonEndangeredAnimal.equals(anotherNonEndangeredAnimal));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    NonEndangeredAnimal savedNonEndangeredAnimal = NonEndangeredAnimal.all().get(0);
    assertEquals(testNonEndangeredAnimal.getId(), savedNonEndangeredAnimal.getId());
  }

  @Test//new test
  public void NonEndangeredAnimal_instantiatesWithAType() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    String savedType = NonEndangeredAnimal.find(testNonEndangeredAnimal.getId()).getType();
    assertEquals("nonendangered", savedType);
  }

  @Test
  public void all_returnsAllInstancesOfNonEndangeredAnimal_false() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Black Bear");
    secondNonEndangeredAnimal.save();
    assertEquals(true, NonEndangeredAnimal.all().get(0).equals(firstNonEndangeredAnimal));
    assertEquals(true, NonEndangeredAnimal.all().get(1).equals(secondNonEndangeredAnimal));
  }

  @Test
  public void find_returnsNonEndangeredAnimalWithSameId_secondNonEndangeredAnimal() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Black Bear");
    secondNonEndangeredAnimal.save();
    assertEquals(NonEndangeredAnimal.find(secondNonEndangeredAnimal.getId()), secondNonEndangeredAnimal);
  }

  @Test//new
   public void updateName_updatesAnimalName_true() {
     NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox");
     firstNonEndangeredAnimal.save();
     firstNonEndangeredAnimal.updateName("Wolf");
     assertEquals("Wolf", NonEndangeredAnimal.find(firstNonEndangeredAnimal.getId()).getName());
   }//assertEquals("Wolf", firstNonEndangeredAnimal.getName());

   @Test//new
   public void delete_deletesAnimal_true() {
     NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox");
     firstNonEndangeredAnimal.save();
     int firstNonEndangeredAnimalId = firstNonEndangeredAnimal.getId();
     firstNonEndangeredAnimal.delete();
     assertEquals(null, NonEndangeredAnimal.find(firstNonEndangeredAnimalId));
   }//assertEquals(0, NonEndangeredAnimal.all().size());


  @Test
  public void find_returnsNullWhenNoNonEndangeredAnimalFound_null() {
    assertTrue(NonEndangeredAnimal.find(999) == null);
  }

}
