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
	public deniro.user.Order order;
	public java.lang.String subscribeTo = "";
	public deniro.user.Order o;
	public com.bitreactive.library.android.maps.model.Position position;
	
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

	public Position startTaxi() {
		return position = new Position (6.3422984E7,1.0394329E7);
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
		Marker ma = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_GREEN).title(alias_taxiID).description("Available").showWindow(true);
		mu.addMarker(ma);
		mu.setCenter(position);
		mu.setZoom(15);
		}
		return mu;
	}
	
	public MapUpdate setAFalse(){
		MapUpdate mu = new MapUpdate();
		if(duty){
		availability = false;
		Marker ma = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_RED).title(alias_taxiID).description("Unavailable").showWindow(true);
		mu.addMarker(ma);
		mu.setCenter(position);
		mu.setZoom(15);
		}
		return mu;
	}
	
	public Order castToOrder(Object o) {
		return (Order)o;
	}
	
	public String createSubscribeTopics() {
		return subscribeTo;
	}

	public Order makeDummyOrder() {
		Order o = new Order(alias_taxiID,"Sunnlandsvegen 64");
		return o;
	}
}
