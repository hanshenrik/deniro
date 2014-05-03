package deniro.taxi;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Position;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class Taxi extends Block {

	public java.lang.String alias_taxiID;
	public java.lang.String type;
	public boolean available;
	public boolean onDuty;
	public deniro.user.Order order;
	public java.lang.String subscribeTo = "";
	public deniro.user.Order o;
	public com.bitreactive.library.android.maps.model.Position position;
	public java.lang.String status;
	public com.bitreactive.library.android.maps.model.MapUpdate mapUpdate;
	public String getStatus() {
		System.out.println("Taxi: getting status");
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String createSubscribeTopics(String taxiID) {
		return taxiID + "," + subscribeTo;
	}
	
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
		System.out.println("Taxi publish OK");
	}
	public void publishError(String error) {
		System.out.println("Taxi: Publish error: "+error);
	}

	public Position startTaxi() {
		return position = new Position (6.3422984E7,1.0394329E7);
	}
	
	public String setOnDuty() {
		this.onDuty = true;
		status = "onduty";
		return status;
	}
	
	public String setOffDuty() {
		onDuty = false;
		// available = false;
		status = "offduty";
		return status;
	}
	
	public String setAvailable() {
		if (onDuty) {
			status = "available";
			return status;
		} else {
			return "error";
		}
	}
	
	public String setUnavailable() {
		if (onDuty) {
			status = "unavailable";
			return status;
		} else {
			return "error";
		}
	}
	
	public MapUpdate setMapUpdate(String status) {
		mapUpdate = new MapUpdate();
		Marker marker = null;
		if (status.equals("onduty") || status.equals("unavailable")) {
			marker = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_RED).
					title(alias_taxiID).description(status).showWindow(true);
		}
		if (status.equals("offduty")) {
			System.out.println("Taxi: setting taxi off duty!");
			marker = Marker.createMarker(alias_taxiID);
			marker.remove();
			mapUpdate.addMarker(marker);
		}
		if (status.equals("available")) {
			marker = Marker.createMarker(alias_taxiID).position(position).hue(Marker.HUE_GREEN).
					title(alias_taxiID).description("Available").showWindow(true);
		}
		
		mapUpdate.addMarker(marker);
		mapUpdate.setCenter(position);
		mapUpdate.setZoom(15);
		return mapUpdate;
	}
	
	public Order castToOrder(Object o) {
		return (Order)o;
	}

	public Order makeDummyOrder() {
		Order o = new Order(alias_taxiID,"Sunnlandsvegen 64");
		return o;
	}
}
