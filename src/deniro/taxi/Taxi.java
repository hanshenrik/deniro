package deniro.taxi;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Position;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class Taxi extends Block {

	public java.lang.String alias_taxiID;
	public java.lang.String type;
	public boolean availability = true;
	public MapPosition position;
	public deniro.user.Order o;
	
	public MapPosition getPosition() {
		return position;
	}

	public void setPosition(MapPosition position) {
		this.position = position;
	}

	public static String getAlias(String alias) {
		return alias;
	}
	
	public static String getAlias(Order order) {
		return order.getUserID();
	}
	
	public String extractMessage(Order order) {
		return order.getOrderInfo();
	}

	public void publishOK() {
		System.out.println("Published");
	}

	public MapUpdate startTaxi() {
		MapUpdate mu = new MapUpdate();
		Marker ma = Marker.createMarker(alias_taxiID).position(new Position (6.3422984E7,1.0394329E7));
		if (availability){
			ma.hue(Marker.HUE_GREEN);
		}
		else
			ma.hue(Marker.HUE_ROSE);
		mu.addMarker(Marker.createMarker(alias_taxiID).position(new Position (6.3422984E7,1.0394329E7)));
		return mu;
	}
}
