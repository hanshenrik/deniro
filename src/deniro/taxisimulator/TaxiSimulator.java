package deniro.taxisimulator;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Polyline;
import com.bitreactive.library.android.maps.model.Position;

import deniro.taxi.MapPosition;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;

public class TaxiSimulator extends Block {
	
	private String curPos = "Elgesetergate 1";
	public java.lang.String taxiAlias;
	public java.lang.String destination = "Sunnlandsvegen 64";
	public no.ntnu.item.ttm4115.simulation.routeplanner.Route r;
	public int routeStepCounter;
	public int steps;
	public Journey makeJourney(){
		Journey j = new Journey(taxiAlias, curPos,destination);
		return j;
	}

	public Route printRoute(Route r) {
		routeStepCounter = 0;
		steps = r.legs.get(0).steps.size();
		for (int i = 0; i < r.legs.get(0).steps.size(); i++) {
			float lat = (float) (r.legs.get(0).steps.get(i).endLocation.latitude*1E6);
			float lng = (float) (r.legs.get(0).steps.get(i).endLocation.longitude*1E6);
			System.out.println("p.addPoint(new Position p"+i+1+"("+lat+","+lng+")");
		}
//		int i = r.legs.get(0).duration.value;
		return r;
	}

	public MapUpdate addPolyline(MapUpdate m){
		m.setCenter(new Position (6.3422984E7,1.0394329E7));
		m.setZoom(15);
		Polyline p1 = new Polyline(taxiAlias);
		Polyline p2 = new Polyline(taxiAlias+"drive");
		p1.hue(Marker.HUE_VIOLET);
		p2.hue(Marker.HUE_CYAN);
		Position endPoint = new Position(0,0);;
		for (int i = routeStepCounter; i < steps; i++) {
			float lat = (float) (r.legs.get(0).steps.get(i).endLocation.latitude*1E6);
			float lng = (float) (r.legs.get(0).steps.get(i).endLocation.longitude*1E6);
			
			if(i==0){p1.addPoint(new Position (lat,lng));}
			if (i+1 == steps) {endPoint = new Position(lat,lng);}
			
			p2.addPoint(new Position (lat,lng));
			
		}
		if (routeStepCounter == r.legs.get(0).steps.size()){
			m.getPolylines().clear();
			Marker m1 = Marker.createMarker(taxiAlias).position(endPoint).hue(Marker.HUE_GREEN).description("Arrived destination").title(taxiAlias);
			m.addMarker(m1);
		}
		else{
			m.addPolyline(p1);
			m.addPolyline(p2);
		}
		routeStepCounter++;
		return m;
	}
}