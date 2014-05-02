package deniro.taxidispatcher;

import java.util.Stack;
import java.util.ArrayList;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;


public class TaxiDispatcher extends Block {

	public java.util.Stack<java.lang.String> registeredTaxis;
	public java.util.Stack<java.lang.String> availableTaxis;
	public java.util.ArrayList<Order> pendingOrders;
	public java.util.ArrayList<Order> confirmedOrders;
	public java.lang.String publishTopic;
	public deniro.user.Order tempOrder;

	public TaxiDispatcher() {
		this.registeredTaxis = new Stack<String>();
		this.availableTaxis = new Stack<String>();
		this.pendingOrders = new ArrayList<Order>();
		this.confirmedOrders = new ArrayList<Order>();
	}
	
	public void taxiOnDuty(String taxiID) {
		System.out.println("on duty "+taxiID);
		registeredTaxis.add(taxiID);
	}
	
	public void taxiOffDuty(String taxiID) {
		System.out.println("off duty "+taxiID);
		registeredTaxis.remove(taxiID);
	}
	
	public void taxiAvailable(String taxiID) {
		System.out.println("available  "+taxiID);
		availableTaxis.add(taxiID);
	}
	
	public void taxiUnavailable(String taxiID) {
		System.out.println("unavailable "+taxiID);
		availableTaxis.remove(taxiID);
	}
	
	public String taxiConfirmed(Order order) {
		System.out.println("TaxiDispatcher: confirming "+order.getOrderInfo());
		pendingOrders.remove(order);
		confirmedOrders.add(order);
		
		return "orderConfirmed";
	}
	
	public String requestHandler(Order order) {
		System.out.println("TaxiDispatcher: creating "+order.getOrderInfo());
		// add distance logic
			
		pendingOrders.add(order);
		return "orderCreated";
	}
	
	public String requestCancelled(Order order) {
		System.out.println("TaxiDispatcher: cancelling "+order.getOrderInfo());
		if (order.getTaxiID() != null) { // no taxi has confirmed yet
			availableTaxis.add(order.getTaxiID());
			confirmedOrders.remove(order);
		} else {
			pendingOrders.remove(order);
		}
		
		return "orderCancelled";
	}

}
