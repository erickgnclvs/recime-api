package br.erickgnclvs.recime_api.service;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.model.Recipe;
import br.erickgnclvs.recime_api.repository.RecipeRepository;
import br.erickgnclvs.recime_api.exception.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private OpenAiChatModel openAiChatModel;

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

    public RecipeDTO getRecipeWithSummary(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseException("Recipe not found", HttpStatus.NOT_FOUND));

        RecipeDTO recipeDTO = new RecipeDTO(recipe);

        if (recipe.getRecipeText() != null && !recipe.getRecipeText().isEmpty()) {
            String summary = summarizeRecipe(recipe.getRecipeText());
            recipeDTO.setSummarizedText(summary);
        }

        return recipeDTO;
    }

    private String summarizeRecipe(String recipeText) {
        Prompt prompt = new Prompt("Summarize this recipe in 3-4 sentences: " + recipeText);
        try {
            ChatResponse response = openAiChatModel.call(prompt);
            return response.getResult().getOutput().getContent();
        } catch (RestClientException e) {
            logger.error("Error calling OpenAI API: ", e);
            throw new ResponseException("Failed to summarize recipe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}