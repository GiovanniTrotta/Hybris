/**
 *
 */
package de.hybris.platform.cuppytrail.facades;

import java.util.List;


public interface StadiumFacade
{
	StadiumData getStadium(String name);

	List<StadiumData> getStadiums();

}