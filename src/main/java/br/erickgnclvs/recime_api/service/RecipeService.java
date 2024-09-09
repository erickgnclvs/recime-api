package br.erickgnclvs.recime_api.service;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.model.Recipe;
import br.erickgnclvs.recime_api.repository.RecipeRepository;
import br.erickgnclvs.recime_api.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDTO> getTrendingRecipes() {
        List<Recipe> recipes = recipeRepository.findAllByOrderByScoreDesc();
        return convertToDTOsWithPosition(recipes);
    }

    public List<RecipeDTO> getFilteredRecipes(String difficultyStr) {
        if (difficultyStr == null || difficultyStr.isEmpty()) {
            throw new ResponseException("A difficulty is required for filtering trending recipes", HttpStatus.BAD_REQUEST);
        }

        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(difficultyStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseException("Invalid difficulty provided. Valid options are: " + Arrays.toString(Difficulty.values()), HttpStatus.BAD_REQUEST);
        }

        List<Recipe> recipes = recipeRepository.findByDifficultyOrderByScoreDesc(difficulty);
        return convertToDTOsWithPosition(recipes);
    }

    private List<RecipeDTO> convertToDTOsWithPosition(List<Recipe> recipes) {
        List<RecipeDTO> recipeDTOs = new ArrayList<>();
        if (recipes != null && !recipes.isEmpty()) {
            for (int i = 0; i < recipes.size(); i++) {
                RecipeDTO dto = new RecipeDTO(recipes.get(i));
                dto.setPosition(i + 1);
                recipeDTOs.add(dto);
            }
        }
        return recipeDTOs;
    }
}