/**
 *
 */
package de.hybris.platform.recipe.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.recipes.model.RecipeModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


/**
 *
 */
@IntegrationTest
public class DefaultRecipeServiceIntegrationTest
{
	@Resource
	private RecipeService recipeService;
	@Resource
	private ModelService modelService;

	private RecipeModel recipeModel;
	private final static String STADIUM_NAME = "wembley";
	private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	@Before
	public void setUp()
	{
		// This instance of a StadiumModel will be used by the tests
		recipeModel = new RecipeModel();
		recipeModel.setCode(STADIUM_NAME);
		recipeModel.setCapacity(STADIUM_CAPACITY);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testFailBehavior()
	{
		modelService.getStadiumForCode(STADIUM_NAME);
	}


	@Test
	public void testStadiumService()
	{
		List<RecipeModel> stadiumModels = recipeService.getRecipe();
		final int size = stadiumModels.size();

		modelService.save(recipeModel);

		stadiumModels = recipeService.getRecipe();
		assertEquals(size + 1, stadiumModels.size());
		assertEquals("Unexpected recipe found", recipeModel, recipeModels.get(recipeModels.size() - 1));

		final RecipeModel persistedReipeModel = recipeService.getRecipeForCode(STADIUM_NAME);
		assertNotNull("No stadium found", persistedReipeModel);
		assertEquals("Different stadium found", recipeModel, persistedReipeModel);
	}

}
