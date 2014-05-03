package deniro.taxicentral;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiCentral extends Block {

	public java.lang.String orderTopics = "userRequest,userCancel,unavailable,confirm";
	public java.lang.String stringTopics = "onduty,offduty,available";
	public java.lang.String subscribeTo = orderTopics + "," + stringTopics;
	
	public String createOrderTopics() {
		return orderTopics;
	}

	public String createStringTopics() {
		return stringTopics;
	}
	
	public Object receivedObject(Object o) {
		Order order = (Order)o;
		System.out.println("TaxiCentral: received this order "+order.getOrderInfo());
		return o;
	}

	public String printThis(String s) {
		System.out.println("TaxiCentral: "+s);
		return s;
	}
}
