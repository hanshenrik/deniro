package deniro.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.ntnu.item.arctis.runtime.Block;
import deniro.user.Order;

public class User extends Block {

	public java.lang.String alias_userID;
	
	public Order createRequest(String request) {
		String[] params = request.split(";");
		
		String address = params[0];
		
		// default values for type and time
		String taxiType = "regular";
		String time = "now";
		
		if (params.length == 2) {
			// we need to find out if time or type is specified
			Pattern pattern = Pattern.compile("(\\d{2}:\\d{2})");
			Matcher matcher = pattern.matcher(params[1]);
			
			if (matcher.find()) {
				time = params[1];
			} else {
				taxiType = params[1];
			}
		}
		
		else if (params.length == 3) {
			taxiType = params[1];
			time = params[2];
		}
		
		Order order = new Order(alias_userID, address, taxiType, time);
		System.out.println("User: Request from user " + order.getUserID() +
				" created at user side! " + order.getOrderInfo());
		return order;
	}

	public String cancelRequest() {
		System.out.println("User: Request from user " + alias_userID + " cancelled at user side!");
		return alias_userID;
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

	public String subscribeTo() {
		return "taxiCentral";
	}
}
