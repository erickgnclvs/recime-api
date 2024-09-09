package br.erickgnclvs.recime_api.repository;

import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByDifficultyOrderByScoreDesc(Difficulty difficulty);

    List<Recipe> findAllByOrderByScoreDesc();
}