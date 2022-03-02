package guru.springframework.bootstrap;

import guru.springframework.model.Category;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {



    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<Recipe>(2);

        Optional<UnitOfMeasure> measureUnitOpt = unitOfMeasureRepository.findByMeasure("Unit");
        Optional<UnitOfMeasure> measureTablespoonOpt = unitOfMeasureRepository.findByMeasure("Tablespoon");
        Optional<UnitOfMeasure> measureTeaspoonOpt = unitOfMeasureRepository.findByMeasure("Teaspoon");
        Optional<UnitOfMeasure> measureDashOpt = unitOfMeasureRepository.findByMeasure("Dash");
        Optional<UnitOfMeasure> measurePinchOpt = unitOfMeasureRepository.findByMeasure("Pinch");
        Optional<UnitOfMeasure> measureCupOpt = unitOfMeasureRepository.findByMeasure("Cup");
        Optional<UnitOfMeasure> measureToTasteOpt = unitOfMeasureRepository.findByMeasure("ToTaste");
        Optional<UnitOfMeasure> measureToServeOpt = unitOfMeasureRepository.findByMeasure("ToServe");

        UnitOfMeasure measureUnit = measureUnitOpt.get();
        UnitOfMeasure measureTablespoon = measureTablespoonOpt.get();
        UnitOfMeasure measureTeaspoon = measureTeaspoonOpt.get();
        UnitOfMeasure measureDash = measureDashOpt.get();
        UnitOfMeasure measurePinch = measurePinchOpt.get();
        UnitOfMeasure measureCup = measureCupOpt.get();
        UnitOfMeasure measureToTaste = measureToTasteOpt.get();
        UnitOfMeasure measureToServe = measureToServeOpt.get();


        Optional<Category> americanCatOpt = categoryRepository.findByCategory("American");
        Optional<Category> mexicanCatOpt = categoryRepository.findByCategory("Mexican");

        Category americanCategory = americanCatOpt.get();
        Category mexicanCategory = mexicanCatOpt.get();

        //Guacamole
        Recipe guacamole = new Recipe();
        guacamole.addCategory(americanCategory);
        guacamole.addCategory(mexicanCategory);
        guacamole.setCooktime(0);
        guacamole.setPreptime(10);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setServings(3);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("Cut the avocados in half. Remove the pit. " +
                "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "Place in a bowl. Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)Sprinkle with salt and lime (or lemon) juice." +
                " The acid in the lime juice will provide some balance to the richness of the avocado and will help delay " +
                "the avocados from turning brown. Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers " +
                "vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole" +
                " to your desired degree of heat. Remember that much of this is done to taste because of the variability in the " +
                "fresh ingredients. Start with this recipe and adjust to your taste. If making a few hours ahead, place plastic " +
                "wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. " +
                "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Garnish with slices of red radish " +
                "or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips." +
                "Refrigerate leftover guacamole up to 3 days. Note: Chilling tomatoes hurts their flavor. " +
                "So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        guacamole.addIngredient("ripe avocados", new BigDecimal(2), measureUnit);
        guacamole.addIngredient("salt, plus more to taste", new BigDecimal(0.25), measureTablespoon);
        guacamole.addIngredient("fresh lime or lemon juice", new BigDecimal(1), measureTablespoon);
        guacamole.addIngredient("serrano or jalapeno chilis, no stems or seeds, minced. More to taste", new BigDecimal(1.5), measureUnit);
        guacamole.addIngredient("minced red or green onion", new BigDecimal(3), measureTablespoon);
        guacamole.addIngredient("cilantro, finely chopped", new BigDecimal(2), measureTablespoon);
        guacamole.addIngredient("freshly ground black pepper", new BigDecimal(1), measurePinch);
        guacamole.addIngredient("ripe tomato", new BigDecimal(0.5), measureUnit);
        guacamole.addIngredient("red radish or jicama, sliced", new BigDecimal(1), measureToTaste);
        guacamole.addIngredient("tortilla chips, to serve", new BigDecimal(1), measureToServe);

        recipes.add(guacamole);

        return recipes;

    }
}
