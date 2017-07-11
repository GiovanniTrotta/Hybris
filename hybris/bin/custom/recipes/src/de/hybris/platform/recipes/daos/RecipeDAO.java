/**
 *
 */
package de.hybris.platform.recipes.daos;



import de.hybris.platform.recipes.model.RecipeModel;

import java.util.List;


public interface RecipeDAO
{
	List<RecipeModel> findRecipeByCode(String id);

	List<RecipeModel> findRecipesByFoodId(String id);

	List<RecipeModel> findRecipesByFoodServings(String id, Integer servings);

}
