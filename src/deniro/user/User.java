package deniro.user;

import no.ntnu.item.arctis.runtime.Block;
import deniro.user.Order;

public class User extends Block {

	public java.lang.String alias_userClientID;
	public java.lang.String pickUpAddress;
	
	public Order createRequest() {
		Order order = new Order(alias_userClientID, "maxi", pickUpAddress);
		System.out.println("User: Request from user " + order.getUserClientID() + "created at user side!");
		return order;
	}

	public String cancelRequest() {
		System.out.println("User: Request from user " + alias_userClientID + " cancelled at user side!");
		return alias_userClientID;
	}
	
	public static String getAlias(String alias) {
		return alias;
	}
	
	public static String getAlias(Order order) {
		return order.getUserClientID();
	}
	
	public String extractMessage(Order order) {
		return order.getOrderInfo();
	}
}
