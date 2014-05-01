package deniro.taxisimulator;

import deniro.taxi.MapPosition;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;

public class TaxiSimulator extends Block {
	
	private String alias_ID;
	private String curPos = "Elgesetergate 1";
	public java.lang.String destination;
	public java.lang.String taxiAlias;
	
	public Journey makeJourney(){
		Journey j = new Journey(taxiAlias, "Elgesetergate 1","Holtermanns veg 45");
		System.out.println(j.toString());
		return j;
	}

	public Route printRoute(Route r) {
		System.out.println(r.toString());
		System.out.println("Legs size: "+r.legs.size());
		System.out.println(r.legs.get(0).steps);
		return r;
	}

}