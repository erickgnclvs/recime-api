package br.erickgnclvs.recime_api.controller;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.service.RecipeService;
import br.erickgnclvs.recime_api.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/trending")
    public ResponseEntity<List<RecipeDTO>> getTrendingRecipes() {
        List<RecipeDTO> recipes = recipeService.getTrendingRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/trending/filter")
    public ResponseEntity<List<RecipeDTO>> getFilteredRecipes(@RequestParam(required = false) String difficulty) {
        List<RecipeDTO> recipes = recipeService.getFilteredRecipes(difficulty);
        return ResponseEntity.ok(recipes);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Map<String, Object>> handleResponseException(ResponseException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", e.getStatus().value());
        response.put("message", e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }
}