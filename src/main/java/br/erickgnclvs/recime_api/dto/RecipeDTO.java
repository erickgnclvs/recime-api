package br.erickgnclvs.recime_api.dto;

import br.erickgnclvs.recime_api.model.Recipe;
import lombok.Data;
import br.erickgnclvs.recime_api.model.Difficulty;

@Data
public class RecipeDTO {
    private Long id;
    private String name;
    private Difficulty difficulty;
    private String imageUrl;
    private int score;
    private int position;
    private String recipeText;
    private String summarizedText;


    public RecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.difficulty = recipe.getDifficulty();
        this.imageUrl = recipe.getImageUrl();
        this.score = recipe.getScore();
        this.recipeText = recipe.getRecipeText();
    }

    public RecipeDTO() {
    }
}