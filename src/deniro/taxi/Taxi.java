package deniro.taxi;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class Taxi extends Block {

	public java.lang.String alias_taxiID;
	public java.lang.String type;
	public boolean availability;
	
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
