package deniro.taxi;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Position;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class Taxi extends Block {

	public java.lang.String alias_taxiID;
	public java.lang.String type;
	public boolean availability;
	public boolean duty;
	public Position position;
	public deniro.user.Order order;
	public java.lang.String subscribeTo = "";
	public deniro.user.Order o;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public static String getAlias(String alias) {
		return alias;
	}
	
	public static String getAlias(Order order) {
		return order.getUserID();
	}
	
	public String extractMessage(Order order) {
		o.setTaxiID(alias_taxiID);
		return order.getOrderInfo();
	}

	public void publishOK() {
		System.out.println("Published");
	}

	public void startTaxi() {
		position = new Position (6.3422984E7,1.0394329E7);
	}
	
	public MapUpdate setOffDuty(){
		duty = false;
		availability=false;
		MapUpdate mu = new MapUpdate();
		Marker ma = Marker.createMarker(alias_taxiID);
		ma.remove();
		mu.addMarker(ma);
		return mu;
	}
		
	public MapUpdate setOnDuty(){
		duty = true;
		return setAFalse();
	}
	
	public MapUpdate setATrue(){
		MapUpdate mu = new MapUpdate();
		if(duty){
		availability = true;
		Marker ma = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_GREEN);
		mu.addMarker(ma);
		}
		return mu;
	}
	
	public MapUpdate setAFalse(){
		MapUpdate mu = new MapUpdate();
		if(duty){
		availability = false;
		Marker ma = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_RED);
		mu.addMarker(ma);
		}
		return mu;
	}
	
	public Order castToOrder(Object o) {
		return (Order)o;
	}
	
	public String createSubscribeTopics() {
		return subscribeTo;
	}
}
