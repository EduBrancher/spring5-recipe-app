package guru.springframework.controllers;

import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @RequestMapping({"index", "index.html", "", "/"})
    public String index(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
