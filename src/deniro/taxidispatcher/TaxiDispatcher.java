package deniro.taxidispatcher;

import java.util.HashMap;
import java.util.Stack;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;


public class TaxiDispatcher extends Block {

	public java.util.Stack<java.lang.String> registeredTaxis;
	public java.util.Stack<java.lang.String> availableTaxis;
	public java.util.HashMap<String, String> tours; // userID, taxiID

	public TaxiDispatcher() {
		this.registeredTaxis = new Stack<String>();
		this.availableTaxis = new Stack<String>();
		this.tours = new HashMap<String, String>();
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
	
	public Order requestHandler(Order order) {
		System.out.println("requesthandler "+order.getOrderInfo());
		// add distance logic
		tours.put(order.getUserID(), availableTaxis.pop());
		return order;
	}
	
	public String requestCancelled(String userID) {
		System.out.println("requestcancelled "+userID);
		availableTaxis.add(tours.get(userID)); // could verify that the order exists, so taxis don't get added several times..
		return tours.remove(userID);
	}

	public void printSomething() {
		System.out.println("TaxiDispatcher started!");
	}
}
