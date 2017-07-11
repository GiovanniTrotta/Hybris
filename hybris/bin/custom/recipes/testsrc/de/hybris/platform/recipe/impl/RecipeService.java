/**
 *
 */
package de.hybris.platform.recipe;

import de.hybris.platform.recipes.model.RecipeModel;

import java.util.List;


public interface RecipeService
{

	List<RecipeModel> getRecipeByCode();

	List<RecipeModel> getRecipeByFoodId();

	List<RecipeModel> getRecipeByFoodServings();



}
