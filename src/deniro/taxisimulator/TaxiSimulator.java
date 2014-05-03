package deniro.taxisimulator;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Polyline;
import com.bitreactive.library.android.maps.model.Position;

import deniro.taxi.MapPosition;
import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;

public class TaxiSimulator extends Block {
	
	public java.lang.String taxiAlias;
	public no.ntnu.item.ttm4115.simulation.routeplanner.Route r;
	public int routeStepCounter;
	public int steps;
	public java.lang.String destination;
	public deniro.user.Order o;
	public com.bitreactive.library.android.maps.model.Position pos;
	
	public Journey makeJourney(Order order){
		destination = order.getAddress();
		taxiAlias = order.getTaxiID();
		float lat = (float) pos.getLatitude()/1000000;
		float lng = (float) pos.getLongitude()/1000000;
		String s =""+lat+","+lng;
		System.out.println(taxiAlias+" to "+destination);
		System.out.println(taxiAlias+" from "+s);
		Journey j = new Journey(taxiAlias,s,destination);
		return j;
	}

	public Route printRoute(Route r) {
		routeStepCounter = 0;
		steps = r.legs.get(0).steps.size();
		for (int i = 0; i < r.legs.get(0).steps.size(); i++) {
			float lat = (float) (r.legs.get(0).steps.get(i).endLocation.latitude*1E6);
			float lng = (float) (r.legs.get(0).steps.get(i).endLocation.longitude*1E6);
			System.out.println("Positions in route Position("+lat+","+lng+")");
		}
//		int i = r.legs.get(0).duration.value;
		return r;
	}

	public MapUpdate addPolyline(MapUpdate m){
		m.setCenter(new Position (6.3422984E7,1.0394329E7));
		m.setZoom(12);
		Polyline p1 = new Polyline(taxiAlias);
		p1.hue(Marker.HUE_CYAN);
		float lat = 0;
		float lng = 0;
		for (int i = routeStepCounter; i < steps; i++) {
			lat = (float) (r.legs.get(0).steps.get(i).endLocation.latitude*1E6);
			lng = (float) (r.legs.get(0).steps.get(i).endLocation.longitude*1E6);			
			p1.addPoint(new Position (lat,lng));
			
		}
		if (routeStepCounter+1 == r.legs.get(0).steps.size()){
			Position endPoint = new Position (lat,lng);
			p1.setRemove();
			Marker m1 = Marker.createMarker(taxiAlias).position(endPoint).hue(Marker.HUE_GREEN).description("Arrived destination").title(taxiAlias).showWindow(true);
			m.addMarker(m1);
			m.addPolyline(p1);
		}
		else{
			m.addPolyline(p1);
		}
		System.out.println("Steps: "+steps);
		System.out.println("StepCounter: "+routeStepCounter);
		routeStepCounter++;
		return m;
	}

	public void updatePos() {
	}
}