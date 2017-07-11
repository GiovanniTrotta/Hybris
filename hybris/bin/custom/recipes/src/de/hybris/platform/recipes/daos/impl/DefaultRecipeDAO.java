/**
 *
 */
package de.hybris.platform.recipes.daos.impl;

import de.hybris.platform.recipes.daos.RecipeDAO;
import de.hybris.platform.recipes.model.FoodModel;
import de.hybris.platform.recipes.model.RecipeModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 */
public class DefaultRecipeDAO implements RecipeDAO
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.recipes.daos.RecipeDAO#findRecipeByCode(java.lang.String)
	 */
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public java.util.List<RecipeModel> findRecipeByCode(final String code)
	{
		// Build a query for the flexible search.
		final String queryString = //
				"SELECT {R:" + RecipeModel.PK + "}" //
						+ "FROM {" + RecipeModel._TYPECODE + " AS r} "//
						+ "WHERE " + "{r:" + RecipeModel.CODERECIPE + "}=?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code);
		return flexibleSearchService.<RecipeModel> search(query).getResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.recipes.daos.RecipeDAO#findRecipesByFoodId(java.lang.String)
	 */
	@Override
	public List<RecipeModel> findRecipesByFoodCode(final String code)
	{

		/*
		 * Select r.name FROM Recipe as r JOIN RecipeEntry as re ON {r.pk}={re.recipe} JOIN Food as f ON re.food=f.PK
		 */
		/* where f.code=code; */


		final String queryString = "SELECT {r:" + RecipeModel.PK + "} " + "FROM {" //
				+ RecipeModel._TYPECODE + " AS r JOIN " + RecipeEntryModel._TYPECODE + //
				" AS re ON r:" + RecipeModel.PK + " = re:" + RecipeEntryModel.RECIPE //
				+ "JOIN " + FoodModel._TYPECODE + " AS f ON re:" + RecipeEntryModel.FOOD + "= f:" + FoodModel.PK //
				+ "} WHERE f:" + FoodModel.CODEFOOD + " = " + code;

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		return flexibleSearchService.<RecipeModel> search(query).getResult();

	}


	@Override
	public List<RecipeModel> findRecipeByFoodServings(final String code, final Integer servings)
	{

		/* Select r.name FROM Recipe as r JOIN Food as f ON {r.pk}={f.recipe} */
		/* where {f.id} like=?id && {r.servings} like =?servings */

		final String queryString = "SELECT {r:" + RecipeModel.PK + "} " + "FROM {" //
				+ RecipeModel._TYPECODE + " AS r JOIN " + RecipeEntryModel._TYPECODE + //
				" AS re ON r:" + RecipeModel.PK + " = re:" + RecipeEntryModel.RECIPE //
				+ "JOIN " + FoodModel._TYPECODE + " AS f ON re:" + RecipeEntryModel.FOOD + "= f:" + FoodModel.PK //
				+ "} WHERE f:" + FoodModel.CODEFOOD + " = " + code + " AND r:" + RecipeModel.SERVINGS + "=" + servings;

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		return flexibleSearchService.<RecipeModel> search(query).getResult();

	}

}