package deniro.taxidispatcher;

import java.util.LinkedList;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;


public class TaxiDispatcher extends Block {

	public java.util.LinkedList<java.lang.String> registeredTaxis; // has to contain taxiID and taxi position, and be indexed!
	public java.util.LinkedList<java.lang.String> availableTaxis;
	public java.util.LinkedList<Order> pendingOrders;
	public java.lang.String publishTopic;
	public deniro.user.Order tempOrder;

	public TaxiDispatcher() {
		this.registeredTaxis = new LinkedList<String>();
		this.availableTaxis = new LinkedList<String>();
		this.pendingOrders = new LinkedList<Order>();
	}
	
	public void taxiOnDuty(String taxiID) {
		System.out.println("on duty "+taxiID);
		registeredTaxis.add(taxiID);
	}
	
	public void taxiOffDuty(String taxiID) {
		System.out.println("off duty (and unavailable) "+taxiID);
		availableTaxis.remove(taxiID);
		registeredTaxis.remove(taxiID);
	}
	
	public void taxiAvailable(String taxiID) {
		System.out.println("available  "+taxiID);
		availableTaxis.add(taxiID);
	}
	
	public String taxiUnavailable(Order order) {
		System.out.println("unavailable "+order.getTaxiID());
		availableTaxis.remove(order.getTaxiID());
		
		if (order.getUserID() == null) {
			return "noReject";
		}
		
		if (!availableTaxis.isEmpty()) {
			return "assignTaxi";
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			return "noTaxiAvailable";
		}
	}
	
	public Order taxiConfirmed(Order order) {
		System.out.println("TaxiDispatcher: confirming "+order.getOrderInfo());
		pendingOrders.remove(order.getUserID());
		order.setMessage("A taxi is on it's way. Enjoy the ride!");
		
		return order;
	}
	
	public boolean requestHandler(Order order) {
		System.out.println("TaxiDispatcher: creating "+order.getOrderInfo());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		pendingOrders.add(order);
		order.setPlaceInQueue(pendingOrders.indexOf(order.getUserID()));
		
		if (!availableTaxis.isEmpty()) {
			return true;
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			return false;
		}
	}
	
	public Order assignTaxi(Order order) {
		// 	add distance logic
		order.setTaxiID(availableTaxis.pop());
		
		return order;
	}
	
	public String requestCancelled(Order order) {
		System.out.println("TaxiDispatcher: cancelling "+order.getOrderInfo());
		// not possible after taxi has confirmed!
		
		availableTaxis.add(order.getTaxiID());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		order.setMessage("Order cancelled!");
		
		return order.getUserID();
	}

	public String getUserID(Order order) {
		return order.getUserID();
	}
	
	public String getTaxiID(Order order) {
		return order.getTaxiID();
	}

}
