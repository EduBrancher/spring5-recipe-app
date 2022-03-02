package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceMap implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceMap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeList = new HashSet<Recipe>();
        recipeRepository.findAll().iterator().forEachRemaining(recipe -> recipeList.add(recipe));
        return recipeList;
    }
}
