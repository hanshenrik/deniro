package deniro.user;

import no.ntnu.item.arctis.runtime.Block;
import deniro.user.Order;

public class User extends Block {

	public java.lang.String alias_userClientID;
	private int counter;

	public User() {
		this.counter = 0;
	}
	
	public Order createRequest() {
		Order order = new Order(counter++, alias_userClientID, "maxi");
		System.out.println("User: Request with orderID "+order.getOrderID() +" created at user "+order.getUserClientID());
		return order;
	}

	public String cancelRequest() {
		int orderID = 0; // make dynamic
		System.out.println("User: Request with orderID " + orderID + " at user " + alias_userClientID + " cancelled!");
		return alias_userClientID;
	}
	
	public static String getAlias(String alias) {
		return alias;
	}
	
	public static String getAlias(Order order) {
		return order.getUserClientID();
	}
	
	public String extractMessage(Order order) {
		return order.getTaxiType(); // change, just for testing for now
	}
}
