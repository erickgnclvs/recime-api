package br.erickgnclvs.recime_api.util;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.model.Recipe;

public class TestDataFactory {

    public static RecipeDTO createRecipeDTO(Long id, String name, Difficulty difficulty, String imageUrl, int score, int position) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDifficulty(difficulty);
        dto.setImageUrl(imageUrl);
        dto.setScore(score);
        dto.setPosition(position);
        return dto;
    }

     public static Recipe createRecipe(Long id, String name, Difficulty difficulty, String imageUrl, int score) {
         Recipe recipe = new Recipe();
         recipe.setId(id);
         recipe.setName(name);
         recipe.setDifficulty(difficulty);
         recipe.setImageUrl(imageUrl);
         recipe.setScore(score);
         return recipe;
     }
}
