package deniro.taxisimulator;

import com.bitreactive.library.android.maps.model.MapUpdate;

import deniro.taxi.MapPosition;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;

public class TaxiSimulator extends Block {
	
	private String curPos = "Elgesetergate 1";
	public java.lang.String taxiAlias;
	public java.lang.String destination = "Sunnlandsvegen 64";
	
	public Journey makeJourney(){
		Journey j = new Journey(taxiAlias, curPos,destination);
		return j;
	}

	public Route printRoute(Route r) {
		System.out.println(r.toString());
		System.out.println("Legs size: "+r.legs.size());
		System.out.println(r.legs.get(0).steps);
		return r;
	}

	public MapUpdate addPolyline(MapUpdate m){
		
		return m;
	}
}