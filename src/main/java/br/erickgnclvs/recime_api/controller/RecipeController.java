package br.erickgnclvs.recime_api.controller;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.service.RecipeService;
import br.erickgnclvs.recime_api.exception.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/trending")
    public ResponseEntity<List<RecipeDTO>> getTrendingRecipes() {
        List<RecipeDTO> recipes = recipeService.getTrendingRecipes();
        return ResponseEntity.ok(recipes);
    }

    // I think it would be a better approach if we did only one request mapping (/trending) with the difficulty param being optional,
    // but as I understood from the code challenge it should be 2 different routes
    @GetMapping("/trending/filter")
    public ResponseEntity<List<RecipeDTO>> getFilteredRecipes(@RequestParam String difficulty) {
        List<RecipeDTO> recipes = recipeService.getFilteredRecipes(difficulty);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}/summarize")
    public ResponseEntity<RecipeDTO> getRecipeWithSummary(@PathVariable Long id) {
        RecipeDTO recipe = recipeService.getRecipeWithSummary(id);
        return ResponseEntity.ok(recipe);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Map<String, Object>> handleResponseException(ResponseException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", e.getStatus().value());
        response.put("message", e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }
}