package deniro.movingtaxi;

import no.ntnu.item.arctis.runtime.Block;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Position;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Duration;
import no.ntnu.item.ttm4115.simulation.routeplanner.Leg;
import no.ntnu.item.ttm4115.simulation.routeplanner.Route;
import no.ntnu.item.ttm4115.simulation.routeplanner.Step;
import no.ntnu.item.ttm4115.simulation.routeplanner.*;


public class MovingTaxi extends Block {

	public Route route;
	public int stepCount;
	public int legCount;

	public MapUpdate createMapUpdate() {
		System.out.println("Creating MapUpdate for taxi: "+route.taxiAlias);
		System.out.println(stepCount);
		if(stepCount >= route.legs.get(legCount).steps.size()){
			legCount++;
			return null;
		}
		Leg leg = route.legs.get(legCount);
		Step step = leg.steps.get(stepCount);
		MapUpdate mapUpdate = new MapUpdate();
		Position position = new Position(step.endLocation.latitude*1E6, step.endLocation.longitude*1E6);
		mapUpdate.addMarker(Marker.createMarker(route.taxiAlias).title(route.taxiAlias).description("Busy...").position(position));
		return mapUpdate;
	
	}

	public int getDelay() {
		if(stepCount >= route.legs.get(legCount).steps.size()) {
			return 0;
		}
		int delay = route.legs.get(legCount).steps.get(stepCount).duration.value;
		System.out.println("Delaying for "+ Integer.toString(delay)+" seconds");
		return delay*10;
	}

	public void setCount() {
		System.out.println("Starting Motion Simulator");
		stepCount = 0;
		legCount = 0;
	}

	public void incrementCounters(MapUpdate update) {
		stepCount++;
	}

}