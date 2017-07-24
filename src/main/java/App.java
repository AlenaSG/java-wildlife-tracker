import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<Animal> allAnimals = new ArrayList<Animal>();
      List<NonEndangeredAnimal> nonendangeredAnimals = NonEndangeredAnimal.all();
      for (Animal animal : nonendangeredAnimals) {
        allAnimals.add(animal);
      }
      List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
      for (Animal animal : endangeredAnimals) {
        allAnimals.add(animal);
      }

      model.put("allAnimals", allAnimals);
      model.put("nonendangeredAnimals", nonendangeredAnimals);
      model.put("endangeredAnimals", endangeredAnimals);
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();

       model.put("nonendangeredanimals", NonEndangeredAnimal.all());
       model.put("endangeredAnimals", EndangeredAnimal.all());
       model.put("template", "templates/animal-form.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/animal/new", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          boolean endangered = request.queryParamsValues("endangered")!=null;
          if (endangered) {
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name);
            endangeredAnimal.setHealth(health);
            endangeredAnimal.setAge(age);
            endangeredAnimal.save();
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
          } else {
            String name = request.queryParams("name");
            NonEndangeredAnimal nonendangeredAnimal = new NonEndangeredAnimal(name);
            nonendangeredAnimal.save();
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
          }
        }, new VelocityTemplateEngine());


    post("/endangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      String site = request.queryParams("site");
      Sighting sighting = new Sighting(animalIdSelected, site, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("endangeredAnimals", EndangeredAnimal.all());
      String endangeredAnimal = EndangeredAnimal.find(animalIdSelected).getName();
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/nonendangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      int animalIdSelected = Integer.parseInt(request.queryParams("nonendangeredAnimalSelected"));
      String site = request.queryParams("site");
      Sighting sighting = new Sighting(animalIdSelected, site, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("nonendangeredAnimals", NonEndangeredAnimal.all());
      String nonendangeredAnimal = NonEndangeredAnimal.find(animalIdSelected).getName();
      model.put("nonendangeredAnimal", nonendangeredAnimal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/nonendangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      NonEndangeredAnimal nonendangeredAnimal = NonEndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("nonendangeredAnimal", nonendangeredAnimal);
      model.put("template", "templates/nonendangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


        get("/error", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/error.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
      }
    }
