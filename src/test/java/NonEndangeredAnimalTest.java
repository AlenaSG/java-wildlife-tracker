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

  // @Test
  // public void delete_deletesNonEndangeredAnimalFromDatabase_0() {
  //   NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
  //   testNonEndangeredAnimal.save();
  //   testNonEndangeredAnimal.delete();
  //   assertEquals(0, NonEndangeredAnimal.all().size());
  // }
  //
  // public void updateName_updatesNonEndangeredAnimalNameInDatabase_String() {
  //   NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
  //   testNonEndangeredAnimal.save();
  //   testNonEndangeredAnimal.updateName("Buck");
  //   assertEquals("Buck", testNonEndangeredAnimal.getName());
  // }

  @Test
  public void find_returnsNullWhenNoNonEndangeredAnimalFound_null() {
    assertTrue(NonEndangeredAnimal.find(999) == null);
  }

}
