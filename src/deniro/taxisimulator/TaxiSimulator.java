package deniro.taxisimulator;

import deniro.taxi.MapPosition;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;

public class TaxiSimulator extends Block {
	
	private String alias_ID;
	private String curPos = "63422424,10395159";
	public java.lang.String destination;
	public java.lang.String taxiAlias;
	
	public Journey makeJourney(){
		return new Journey(taxiAlias, curPos, destination);
	}

}